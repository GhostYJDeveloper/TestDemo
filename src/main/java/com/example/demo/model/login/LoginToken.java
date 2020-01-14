package com.example.demo.model.login;

import com.example.demo.common.SnowFlake;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 登录类
 */
public class LoginToken {
    public LoginToken() {

    }

    /**
     * 插入
     *
     * @param loginType
     * @param loginId
     * @param recordTime
     */
    public LoginToken(LoginTypeEnum loginType, Long loginId,Date recordTime) {
        this.id = SnowFlake.instant().nextId();
        this.loginType = loginType;
        this.loginId = loginId;
        this.recordTime=recordTime;
    }

    /**
     * 从仓储还原
     *
     * @param id
     * @param loginType
     * @param loginId
     * @param recordTime
     */
    public LoginToken(long id, LoginTypeEnum loginType, Long loginId,Date recordTime) {
        this.id = id;
        this.loginType = loginType;
        this.loginId = loginId;
        this.recordTime=recordTime;
    }

    /**
     * Id
     */
    public Long id;

    /// <summary>
    /// 登录用户类型
    /// </summary>
    public LoginTypeEnum loginType;

    /// <summary>
    /// 登录用户Id
    /// </summary>
    public Long loginId;

    /// <summary>
    /// 提交日期
    /// </summary>
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date recordTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoginTypeEnum getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginTypeEnum loginType) {
        this.loginType = loginType;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}
