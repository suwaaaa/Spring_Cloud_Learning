package com.Service;

import com.Entity.RequestResult;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;

@Service
public interface FileUploadService {

    @PostMapping(value = "/requestUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    RequestResult fileUpload(@RequestPart("myFile") MultipartFile file) throws FileNotFoundException;

//    @RequestMapping(value = "/requestDownload", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
//    void fileDownload(HttpServletResponse response);

    @GetMapping(value = "/localDownload")
    ResponseEntity<InputStreamResource> localSererFileDownload(String fileName) throws FileNotFoundException;
}
