package com.example.demo.model.warehouse;

/**
 * 货物类别
 */
public enum WarehouseTypeEnum {

    运动类(1, "SUPPORT"),
    食品类(2, "FOOD"),
    家具类(3, "FURNITURE"),
    电脑类(4, "COMPUTER"),
    键盘类(5, "KEYBOARD");

    // 成员变量
    private Integer code;
    private String name;

    // 构造方法
    WarehouseTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    //普通方法
    public static String getName(int code) {
        for (WarehouseTypeEnum c : WarehouseTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
    public static Integer getCode(String name) {
        for (WarehouseTypeEnum c : WarehouseTypeEnum.values()) {
            if (c.getName() == name) {
                return c.code;
            }
        }
        return null;
    }

    public static WarehouseTypeEnum getWarehouseType(int code) {
        for (WarehouseTypeEnum c : WarehouseTypeEnum.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    public static WarehouseTypeEnum getWarehouseType(String name) {
        for (WarehouseTypeEnum c : WarehouseTypeEnum.values()) {
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
