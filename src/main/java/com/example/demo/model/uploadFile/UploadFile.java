package com.example.demo.model.uploadFile;

import com.example.demo.common.SnowFlake;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UploadFile {
    private Long urId;

    private String urDomainname;

    private Long urDomainid;

    private String urFilename;

    private String urFiledownloaduri;

    private String urFiletype;

    private Long urSize;

    private String urCustominfo;

    private String urSavePath;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date urRecordtime;


    public UploadFile(String urDomainname, Long urDomainid, String urFilename, String urFiledownloaduri, String urFiletype, Long urSize, String urCustominfo,String urSavePath) {
        this.urId= SnowFlake.instant().nextId();
        this.urDomainname = urDomainname;
        this.urDomainid = urDomainid;
        this.urFilename = urFilename;
        this.urFiledownloaduri = urFiledownloaduri;
        this.urFiletype = urFiletype;
        this.urSize = urSize;
        this.urCustominfo = urCustominfo;
        this.urSavePath=urSavePath;
        this.urRecordtime = new Date();
    }

    /**
     * 从仓储还原
     * @param urId
     * @param urDomainname
     * @param urDomainid
     * @param urFilename
     * @param urFiledownloaduri
     * @param urFiletype
     * @param urSize
     * @param urCustominfo
     * @param urRecordtime
     */
    public UploadFile(Long urId, String urDomainname, Long urDomainid, String urFilename, String urFiledownloaduri, String urFiletype, Long urSize, String urCustominfo,String urSavePath,Date urRecordtime) {
        this.urId = urId;
        this.urDomainname = urDomainname;
        this.urDomainid = urDomainid;
        this.urFilename = urFilename;
        this.urFiledownloaduri = urFiledownloaduri;
        this.urFiletype = urFiletype;
        this.urSize = urSize;
        this.urCustominfo = urCustominfo;
        this.urSavePath=urSavePath;
        this.urRecordtime = urRecordtime;
    }

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

    public String getUrSavePath() {
        return urSavePath;
    }

    public void setUrSavePath(String urSavePath) {
        this.urSavePath = urSavePath;
    }

    public Date getUrRecordtime() {
        return urRecordtime;
    }

    public void setUrRecordtime(Date urRecordtime) {
        this.urRecordtime = urRecordtime;
    }
}