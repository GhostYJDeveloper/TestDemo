package com.example.demo.model.user;
import com.example.demo.common.SnowFlake;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class User {
    private long id;
    private String chineseName;
    private String userName;
    private String passWord;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 钱
     */
    @Value("0")
    private BigDecimal money;
    /**
     * 上次充值时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rechargeTime;
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
    public User(Long id,String userName,String chineseName,  String passWord, Date createTime,Date updateTime,BigDecimal money,Date rechargeTime) {
        this.id =id;
        this.chineseName = chineseName;
        this.userName = userName;
        this.passWord = passWord;
        this.createTime = createTime;
        this.updateTime=updateTime;
        this.money=money;
        this.rechargeTime=rechargeTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", chineseName=" + chineseName + ", userName=" + userName +", passWord=" + passWord + "]";
    }
}
