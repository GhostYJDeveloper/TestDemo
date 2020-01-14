package com.example.demo.model.login;
//登录令牌枚举类
public enum LoginTypeEnum {
    普通用户(1, "普通用户"),
    管理员(2, "管理员");

    // 成员变量
    private Integer code;
    private String name;

    // 构造方法
    LoginTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    //普通方法
    public static String getName(int code) {
        for (LoginTypeEnum c : LoginTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
    public static Integer getCode(String name) {
        for (LoginTypeEnum c : LoginTypeEnum.values()) {
            if (c.getName() == name) {
                return c.code;
            }
        }
        return null;
    }

    public static LoginTypeEnum getLoginTokenType(int code) {
        for (LoginTypeEnum c : LoginTypeEnum.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static LoginTypeEnum getLoginTokenType(String name) {
        for (LoginTypeEnum c : LoginTypeEnum.values()) {
            if (c.getName() == name) {
                return c;
            }
        }
        return null;
    }

    public Integer getCode(){return code;}
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
