package com.dysy.demo.pojo;

/**
 * @author: Dai Junfeng
 * @create: 2020-08-31
 **/
public class FileUploadResult {
    private String fileOriginName;
    private long fileSize;
    private String fileType;
    private String fileName;
    private String imageUrl;
    private String downloadUri;

    public FileUploadResult() { }

    public FileUploadResult(String fileOriginName, long fileSize, String fileType, String fileName, String imageUrl, String downloadUri) {
        this.fileOriginName = fileOriginName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.fileName = fileName;
        this.imageUrl = imageUrl;
        this.downloadUri = downloadUri;
    }

    public String getFileOriginName() {
        return fileOriginName;
    }

    public void setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
