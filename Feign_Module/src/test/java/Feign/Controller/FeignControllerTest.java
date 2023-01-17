package Feign.Controller;

import Feign.Entity.RequestResult;
import Feign.Service.RequestService;
import feign.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FeignControllerTest {

    @Mock
    private RequestService mockRequestService;

    private FeignController feignControllerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        feignControllerUnderTest = new FeignController(mockRequestService);
    }

    @Test
    public void testHelloString() {
        // Setup
        when(mockRequestService.helloString()).thenReturn("result");

        // Run the test
        final String result = feignControllerUnderTest.helloString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testFileUpload() {
        // Setup
        final MultipartFile file = null;
        final RequestResult expectedResult = new RequestResult(0, "requestRequestValue");

        // Configure RequestService.fileUpload(...).
        final RequestResult requestResult = new RequestResult(0, "requestRequestValue");
        when(mockRequestService.fileUpload(any(MultipartFile.class))).thenReturn(requestResult);

        // Run the test
        final RequestResult result = feignControllerUnderTest.fileUpload(file);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

//    @Test
//    public void testFileDownload() {
//        // Setup
//
//        // Configure RequestService.fileDownload(...).
//        final Response spyResponse = spy(Response.builder().build());
//        when(mockRequestService.fileDownload()).thenReturn(spyResponse);
//
//        // Run the test
//        final ResponseEntity<byte[]> result = feignControllerUnderTest.fileDownload("fileName");
//
//        // Verify the results
//        verify(spyResponse).close();
//    }
}
