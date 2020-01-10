package com.example.demo.domain.user;

import com.example.demo.common.SnowFlake;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private long id;
    private String chineseName;
    private String userName;
    private String passWord;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public User() {

    }

    public User(String userName,String chineseName, String passWord, Date createTime) {
        this.id = SnowFlake.instant().nextId();
        this.chineseName = chineseName;
        this.userName = userName;
        this.passWord = passWord;
        this.createTime = createTime;
    }

    /**
     * 从仓储还原
     * @param id
     * @param userName
     * @param chineseName
     * @param passWord
     * @param createTime
     */
    public User(Long id,String userName,String chineseName,  String passWord, Date createTime) {
        this.id =id;
        this.chineseName = chineseName;
        this.userName = userName;
        this.passWord = passWord;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", chineseName=" + chineseName + ", userName=" + userName +", passWord=" + passWord + "]";
    }
}
