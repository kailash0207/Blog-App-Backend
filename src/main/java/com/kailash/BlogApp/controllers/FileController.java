package com.kailash.BlogApp.controllers;

import com.kailash.BlogApp.models.ApiResponse;
import com.kailash.BlogApp.models.FileRequestType;
import com.kailash.BlogApp.services.impl.FileServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static com.kailash.BlogApp.utils.Constants.ApiConstants.*;
import static com.kailash.BlogApp.utils.Constants.ErrorMessages.*;
import static com.kailash.BlogApp.utils.Constants.SuccessMessages.FILE_FETCH_SUCCESSFUL;
import static com.kailash.BlogApp.utils.Constants.SuccessMessages.FILE_UPLOAD_SUCCESSFUL;
import static com.kailash.BlogApp.utils.Constants.FileTypes.*;

@RestController
@RequestMapping(FILES_API_PREFIX)
public class FileController {
    @Autowired
    private FileServiceImpl fileService;
    private static final String BASE_PATH = "files";
    @RequestMapping(
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public @ResponseBody ApiResponse uploadFile(@RequestParam(FILE) MultipartFile file,
                                                @RequestParam(REQUEST_TYPE) Integer requestType) throws IOException {
        FileRequestType fileRequestType;
        try {
            fileRequestType = FileRequestType.values()[requestType];
        }catch (ArrayIndexOutOfBoundsException e){
            return new ApiResponse(INVALID_REQUEST, HttpStatus.BAD_REQUEST, false, Map.of(REQUEST_TYPE, requestType));
        }
        assert fileRequestType != null;
        String path = BASE_PATH + File.separator + fileRequestType.getPath();
        if(isValidFileType(file, fileRequestType)) {
            String fileName = fileService.uploadFile(path, file);
            return new ApiResponse(FILE_UPLOAD_SUCCESSFUL, HttpStatus.CREATED, true, Map.of(FILE_NAME, fileName));
        }else{
            return new ApiResponse(INVALID_FILE_TYPE, HttpStatus.BAD_REQUEST, false, Map.of(FILE_NAME, file.getOriginalFilename()));
        }

    }

    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public @ResponseBody ApiResponse getFile(@RequestParam(FILE_NAME) String fileName,
                        @RequestParam(REQUEST_TYPE) Integer requestType,
                        HttpServletResponse response){
        FileRequestType fileRequestType;
        try {
            fileRequestType = FileRequestType.values()[requestType];
        }catch (ArrayIndexOutOfBoundsException e){
            return new ApiResponse(INVALID_REQUEST, HttpStatus.BAD_REQUEST, false, Map.of(REQUEST_TYPE, requestType));
        }
        assert fileRequestType != null;
        String path = BASE_PATH + File.separator + fileRequestType.getPath();
        try {
            InputStream inputStream = fileService.getFile(path, fileName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(inputStream, response.getOutputStream());
            return new ApiResponse(FILE_FETCH_SUCCESSFUL, HttpStatus.OK, true, Map.of(FILE_NAME, fileName));
        } catch (FileNotFoundException e) {
            return new ApiResponse(FILE_NOT_FOUND, HttpStatus.NOT_FOUND, false, Map.of(FILE_NAME, fileName));
        } catch (IOException e) {
            return new ApiResponse(ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR, false, Map.of(FILE_NAME, fileName));
        }

    }

    private Boolean isValidFileType(MultipartFile file, FileRequestType fileRequestType){
        if(fileRequestType.getFileType().equals(IMAGE)){
            return file != null && file.getContentType() != null &&
                    file.getContentType().startsWith(IMAGE);
        }
        return false;
    }
}
