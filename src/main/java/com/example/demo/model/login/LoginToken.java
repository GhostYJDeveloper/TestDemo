package com.example.demo.model.login;

import com.example.demo.common.SnowFlake;

/**
 * 登录类
 */
public class LoginToken {
    public LoginToken(){

    }

    public LoginToken(String LoginType,String LoginId)
    {
        Id = SnowFlake.instant().nextId();
        this.LoginType=LoginType;
        this.LoginId=LoginId;
    }

    /**
     * Id
     */
    public Long Id;

    /// <summary>
    /// 登录用户类型
    /// </summary>
    public String LoginType;

    /// <summary>
    /// 登录用户Id
    /// </summary>
    public String LoginId;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLoginType() {
        return LoginType;
    }

    public void setLoginType(String loginType) {
        LoginType = loginType;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }


}
