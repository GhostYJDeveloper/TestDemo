package com.example.demo.common;

import com.example.demo.model.login.LoginToken;
import com.example.demo.model.mapper.LoginTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
@Component
public class LoginTokenHelper {
    public LoginTokenHelper(){

    }
    @Autowired
    private LoginTokenMapper loginTokenMapper;

    private static LoginTokenHelper loginTokenHelper;
    //用此方法才能注入Mapper对象
    @PostConstruct
    public void init() {
        loginTokenHelper = this;
        loginTokenHelper.loginTokenMapper = this.loginTokenMapper;
    }

    /**
     * 判断登录令牌是否失效
     * @param session
     * @return
     */
    public static boolean isLoginTokenDisabled(HttpSession session){
        //先通过Session判断令牌有无失效
        LoginToken loginToken = (LoginToken) session.getAttribute("loginToken");
        if (loginToken == null) {
            return false;
        }
        //如果Session没有失效，再去表里查看令牌有没有被删除
        else {
            loginToken =loginTokenHelper.loginTokenMapper.selectById(loginToken.getId());
            if (loginToken == null) {
                return false;
            }
        }
        return true;
    }
}
