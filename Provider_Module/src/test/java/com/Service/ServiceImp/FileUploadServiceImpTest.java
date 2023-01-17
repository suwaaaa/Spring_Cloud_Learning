package com.Service.ServiceImp;

import com.Entity.RequestResult;
import com.Utils.FileProperty;
import com.Utils.ResultCode;
import org.json.JSONString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadServiceImpTest {

    @SpyBean
//    @MockBean
    private FileProperty mockFileProperty;

    private FileUploadServiceImp fileUploadServiceImpUnderTest;

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;


    @Before
    public void setUp() {
        initMocks(this);
        fileUploadServiceImpUnderTest = new FileUploadServiceImp(mockFileProperty);
        //MockRestServiceServer
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
       /* mockRestServiceServer
                .expect(ExpectedCount.times(1), MockRestRequestMatchers
                                                .requestTo(("http://localhost:9020/sendThisRequestForTest")))//Matchers.startsWithIgnoringCase
                .andRespond(withSuccess("{\"code\": 200}", MediaType.APPLICATION_JSON));*/

            //MockitoAnnotations.initMocks(this);//Mockito-01
    }
            // 插桩先行//Mockito-02
            //Mockito.doAnswer(answer).when(mock).callMethod(ArgMatcher...);
            // 插桩后行//Mockito-03
            //Mockito.when(mock.callMethod(ArgMatcher...)).doAnswer(answer);

    @Test
    public void testMockRestServiceServerByJavon() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:9020/sendThisRequestForTest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("testMockRestServiceServerByJavon Result Test By Javon: Finish");
        assertThat(result).asString().containsPattern("Poole");
    }

    @Test
    public void testFileUpload() throws Exception {
        // Setup
//        final RequestResult expectedResult = new RequestResult(0, "requestRequestValue");
        when(mockFileProperty.getLimitSize()).thenReturn(1111);
        when(mockFileProperty.getAllowTypes()).thenReturn(Collections.singletonList("txt"));
        when(mockFileProperty.getPath()).thenReturn("result");
        String  mckRestServiceServerResult;
        mckRestServiceServerResult = mockMvc// Run the test
                .perform(
                        MockMvcRequestBuilders.multipart("/requestUpload")
                .file(
                        new MockMultipartFile("myFile",
                                    "123.txt",
                                    ",multipart/form-data",
                                                "hello upload".getBytes(StandardCharsets.UTF_8))))
                .andExpect(MockMvcResultMatchers.status().isOk())//.isNotFound()    // Verify the results
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("testFileUpload Test Result: " + mckRestServiceServerResult);
        RequestResult myFileRealUploadResult = fileUploadServiceImpUnderTest.fileUpload(new MockMultipartFile("myFile",
                "123.txt",
                ",multipart/form-data",
                "hello upload".getBytes(StandardCharsets.UTF_8)));
        System.out.println("testFileUpload Real Result: " + myFileRealUploadResult);
        assertThat(mckRestServiceServerResult).contains(myFileRealUploadResult.getRequestRequestValue());
    }

    @Test
    public void testFileUpload_ThrowsFileNotFoundException() {
        // Setup    //  Run the test
        assertThatThrownBy(() -> {
            mockMvc.perform(
                    MockMvcRequestBuilders.multipart("/requestUpload")
                            .file(null))
                    .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                    .andReturn().getResponse();
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("must not be null");
    }

    @Test
    public void testLocalSererFileDownload() throws Exception {
        // Setup
        final ResponseEntity<InputStreamResource> expectedResult;
        expectedResult = new ResponseEntity<>(
                new InputStreamResource(
                        new ByteArrayInputStream("hello upload".getBytes()), "resource loaded through InputStream"
                ),
                HttpStatus.OK
        );
        when(mockFileProperty.getPath()).thenReturn("Receive_File/");
        // Run the test
        final ResponseEntity<InputStreamResource> result = fileUploadServiceImpUnderTest.localSererFileDownload("123.txt");
        // Verify the results  "resource loaded through InputStream"
        assertThat(Objects.requireNonNull(expectedResult.getBody()).getDescription()).isIn(Objects.requireNonNull(result.getBody()).getDescription());
    }

    @Test
    public void testLocalSererFileDownload_ThrowsFileNotFoundException() {
        // Setup
        when(mockFileProperty.getPath()).thenReturn("Receive_File/");
        // Run the test
        assertThatThrownBy(() -> {
            fileUploadServiceImpUnderTest.localSererFileDownload("1");
        }).isInstanceOf(FileNotFoundException.class).hasMessageContaining("系统找不到指定的文件");
    }

    @Test
    public void testFileTypeValidationPass_FileTypeNotMatch() /*throws NoSuchMethodException, InvocationTargetException, IllegalAccessException*/ {
        /*System.out.println(Arrays.toString(fileUploadServiceImpUnderTest.getClass().getDeclaredMethods()));
        Method fileTypeValidationPassMethod = fileUploadServiceImpUnderTest.getClass().getDeclaredMethod("fileTypeValidationPass");
        fileTypeValidationPassMethod.setAccessible(true);
        Object fileTypeValidationPassMethodResult = fileTypeValidationPassMethod.invoke(fileUploadServiceImpUnderTest);
        System.out.println("Javon testFileTypeValidationPass_FileTypeNotMatch Test: " + fileTypeValidationPassMethodResult);*/
        String testFileType = ".ppt";
        Object o = ReflectionTestUtils.invokeMethod(fileUploadServiceImpUnderTest, "fileTypeValidationPass", testFileType);
//        Object o = ReflectionTestUtils.invokeMethod(FileUploadServiceImp.class, "fileTypeValidationPass", testFileType);
        System.out.println("Javon testFileTypeValidationPass_FileTypeNotMatch Test: " + testFileType + o);
        assertThat(o).asString().contains(ResultCode.FILE_REQUEST_NOT_SUPPORT_TYPE.getMessageValue());
    }

    @Test
    public void testFileTypeValidationPass_FileTypeDoMatch(){
        String testFileType = ".txt";
        Object o = ReflectionTestUtils.invokeMethod(fileUploadServiceImpUnderTest, "fileTypeValidationPass", testFileType);
//        Object o = ReflectionTestUtils.invokeMethod(FileUploadServiceImp.class, "fileTypeValidationPass", testFileType);
        System.out.println("Javon testFileTypeValidationPass_FileTypeDoMatch Test: " + testFileType + o);
        assertThat(o).asString().contains(ResultCode.FILE_REQUEST_SUPPORT_TYPE_ALLOW.getMessageValue());
    }

    @Test
    public void testFileUploadPathInit_WithFail(){
        String testFilePath = "F:\\workSpace\\ideaIU-2018.2.8\\base_project\\Java_IBM_Learning\\Provider_Module" +
                "\\target\\test-classes\\Receive_File\\test";
        File file = new File(testFilePath);
        //通过File类对象调用，删除File类对象对应的文件或者文件夹
        //只能删除空文件夹
        deleteFile(file);
        Object o = ReflectionTestUtils.invokeMethod(fileUploadServiceImpUnderTest, "fileUploadPathInit", testFilePath);
        System.out.println("  Javon testFileUploadPathInit_WithFail Test: " + testFilePath + o);
        assertThat(o).asString().contains("true");
    }
    private static void deleteFile(File file) {
        if(!file.exists()){//判断文件是否存在
            System.out.println("文件不存在！");
        }else{
            File files[]=file.listFiles();
            for(File neile: Objects.requireNonNull(files)){//遍历文件夹下的目录
                if(neile.isFile()){//如果是文件而不是文件夹==>可直接删除
                    System.out.println("已删除"+neile.getName());
                    neile.delete();
                }else{
                    deleteFile(neile);//是文件夹,递归调用方法
                }
            }
            System.out.println("已删除"+file.getName());
            file.delete();
        }
    }

    @Test
    public void testFileUploadPathInit_WithSuccess(){
        String testFilePath = "F:\\workSpace\\ideaIU-2018.2.8\\base_project\\Java_IBM_Learning\\Provider_Module" +
                "\\target\\test-classes\\Receive_File\\123.txt";
        Object o = ReflectionTestUtils.invokeMethod(fileUploadServiceImpUnderTest, "fileUploadPathInit", testFilePath);
        System.out.println("Javon testFileUploadPathInit_WithSuccess Test: " + testFilePath + o);
        assertThat(o).asString().contains("false");
    }

    @Test
    public void testFileTypeSizeValidaction_WithFail_WhenUploadFile() throws Exception {
        File file = new File("F:\\Receive_File\\myFile.pdf");
        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("myFile", fileInputStream);
        String contentAsString = mockMvc// Run the test
                .perform(
                        MockMvcRequestBuilders.multipart("/requestUpload")
                                .file(mockMultipartFile))
//                .andExpect(MockMvcResultMatchers.status().is4xxClientError()) // Verify the results
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("testFileTypeSizeValidaction_WithFail_WhenUploadFile -- " + contentAsString + " getName " + mockMultipartFile.getName());
        assertThat(contentAsString).asString().contains(ResultCode.FILE_REQUEST_SIZE_TOO_LARGE.getMessageValue());
    }
}
