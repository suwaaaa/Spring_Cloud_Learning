package com.Service.ServiceImp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@AutoConfigureMockMvc
public class ConsumerServiceImpTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private ConsumerServiceImp consumerServiceImpUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        consumerServiceImpUnderTest = new ConsumerServiceImp(mockRestTemplate);
    }

    @Test
    public void testWelcome() {
        final String getResult = consumerServiceImpUnderTest.welcome();
        System.out.println("testWelcome Result: " + getResult);
        Assert.assertEquals("Hello, This is Spring-Cloud-Eureka-Consumer 01",getResult);
//        assertThat(getResult).isEqualTo("Hello, This is Spring-Cloud-Eureka-Consumer 01");
    }

    @Test
    public void testHellString() {
        // Setup
        when(mockRestTemplate
                .getForEntity(eq("http://SPRING-CLOUD-EUREKA-PROVIDER-01/service9020/hello"), eq(String.class), any(Object.class)))
                .thenReturn(new ResponseEntity<>("", HttpStatus.OK));
        // Run the test
        final String result = consumerServiceImpUnderTest.hellString();
        // Verify the results
        System.out.println("testHellString: Result: " + result);
        assertThat(result).asString().contains("调用远程Spring Cloud的Provider服务(服务器-9020)");
    }


    @Test
    public void testHellString_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate
                .getForEntity(eq("http://SPRING-CLOUD-EUREKA-PROVIDER-01/service9020/hello"), eq(String.class), any(Object.class)))
                .thenThrow(RestClientException.class);
        // Run the test
        final String result = consumerServiceImpUnderTest.hellString();
        System.out.println("testHellString_RestTemplateThrowsRestClientException Result: " + result);
        // Verify the results
        assertThat(result).asString().contains("[失败]");
    }

    @Test
    public void testHystrix() {
        // Setup
        when(mockRestTemplate
                .getForEntity(eq("http://SPRING-CLOUD-EUREKA-PROVIDER-01/service9020/hello"), eq(String.class), any(Object.class)))
                .thenReturn(new ResponseEntity<>("", HttpStatus.OK));
        // Run the test
        final String result = consumerServiceImpUnderTest.hystrix();
        // Verify the results
        System.out.println("testHystrix Result: " + result);
        assertThat(result).asString().contains("成功");
    }

    @Test
    public void testHystrix_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate
                .getForEntity(eq("http://SPRING-CLOUD-EUREKA-PROVIDER-01/service9020/hello"), eq(String.class), any(Object.class)))
                .thenThrow(RestClientException.class);

        // Run the test
        final String result = consumerServiceImpUnderTest.hystrix();
        System.out.println("testHystrix_RestTemplateThrowsRestClientException Result: " + result);
        // Verify the results
        assertThat(result).asString().contains("失败调用");
    }
}
