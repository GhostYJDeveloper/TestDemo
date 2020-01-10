package com.example.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserConfig {

    private Long id;
    private String chineseName;
    private String userName;
    private String password;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public UserConfig(){

    }

    /**
     * 从仓储还原
     * @param id
     * @param chineseName
     * @param userName
     * @param password
     * @param createTime
     */
    public UserConfig(Long id, String chineseName, String userName, String password, Date createTime) {
        this.id = id;
        this.chineseName = chineseName;
        this.userName = userName;
        this.password = password;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
