package com.example.demo.dto;

import com.example.demo.common.SnowFlake;

import java.util.Date;

public class UploadFileResponse {

    private long id;
    private String domainName;
    private long domainId;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String customInfo;
    private Date recordTime;


    public UploadFileResponse(String domainName,long domainId, String fileName, String fileDownloadUri, String fileType, long size,String customInfo) {
        id= SnowFlake.instant().nextId();
        this.domainName = domainName;
        this.domainId = domainId;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.recordTime = new Date();
        this.customInfo = customInfo;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public long getDomainId() {
        return domainId;
    }

    public void setDomainId(long domainId) {
        this.domainId = domainId;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
    public String getCustomInfo() {
        return customInfo;
    }

    public void setCustomInfo(String customInfo) {
        this.customInfo = customInfo;
    }

}