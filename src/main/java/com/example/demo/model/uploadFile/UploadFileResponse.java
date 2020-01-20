package com.example.demo.model.uploadFile;

import java.util.Date;

public class UploadFileResponse {
    private Long urId;

    private String urDomainname;

    private Long urDomainid;

    private String urFilename;

    private String urFiledownloaduri;

    private String urFiletype;

    private Long urSize;

    private String urCustominfo;

    private Date urRecordtime;

    public Long getUrId() {
        return urId;
    }

    public void setUrId(Long urId) {
        this.urId = urId;
    }

    public String getUrDomainname() {
        return urDomainname;
    }

    public void setUrDomainname(String urDomainname) {
        this.urDomainname = urDomainname == null ? null : urDomainname.trim();
    }

    public Long getUrDomainid() {
        return urDomainid;
    }

    public void setUrDomainid(Long urDomainid) {
        this.urDomainid = urDomainid;
    }

    public String getUrFilename() {
        return urFilename;
    }

    public void setUrFilename(String urFilename) {
        this.urFilename = urFilename == null ? null : urFilename.trim();
    }

    public String getUrFiledownloaduri() {
        return urFiledownloaduri;
    }

    public void setUrFiledownloaduri(String urFiledownloaduri) {
        this.urFiledownloaduri = urFiledownloaduri == null ? null : urFiledownloaduri.trim();
    }

    public String getUrFiletype() {
        return urFiletype;
    }

    public void setUrFiletype(String urFiletype) {
        this.urFiletype = urFiletype == null ? null : urFiletype.trim();
    }

    public Long getUrSize() {
        return urSize;
    }

    public void setUrSize(Long urSize) {
        this.urSize = urSize;
    }

    public String getUrCustominfo() {
        return urCustominfo;
    }

    public void setUrCustominfo(String urCustominfo) {
        this.urCustominfo = urCustominfo == null ? null : urCustominfo.trim();
    }

    public Date getUrRecordtime() {
        return urRecordtime;
    }

    public void setUrRecordtime(Date urRecordtime) {
        this.urRecordtime = urRecordtime;
    }
}