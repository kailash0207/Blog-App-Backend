package com.kailash.BlogApp.models;



import com.kailash.BlogApp.utils.Constants.*;

public enum FileRequestType{
    POST_IMAGE("post_image", FileTypes.IMAGE);
    private final String path;
    private final String fileType;

    FileRequestType(String path, String fileType){
        this.path = path;
        this.fileType = fileType;
    }
    public String getPath(){
        return this.path;
    }
    public String getFileType(){
        return this.fileType;
    }
}
