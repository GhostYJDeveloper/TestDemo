package com.example.demo.model.order;

import com.example.demo.model.warehouse.WarehouseTypeEnum;

import java.util.Date;

/**
 * 订单列表展示类，用于合并货物和订单信息
 */
public class OrderConfig {

    public OrderConfig(Order order,String warehouseName,WarehouseTypeEnum type){
        this.id=order.getId();
        this.userId = order.getUserId();
        this.userChineseName = order.getUserChineseName();
        this.cargoNumber = order.getCargoNumber();
        this.orderNumber = order.getOrderNumber();
        this.cargoName = order.getCargoName();
        this.orderDate = order.getOrderDate();
        this.warehouseName=warehouseName;
        this.type=type;
        this.buyCount = order.getBuyCount();
    }


    /**
     * 订单Id
     */
    private long id;

    /**
     * 下单用户Id
     */
    private long userId;

    /**
     * 下单用户中文名
     */
    private String userChineseName;

    /**
     * 货物编号
     */
    private String cargoNumber;

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    /**
     * 货物名称
     */
    private String cargoName;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 下单日期
     */
    private Date orderDate;

    /**
     * 货物名称
     */
    private String warehouseName;

    /**
     * 货物类型
     */
    private WarehouseTypeEnum type;
    /**
     * 下单数量
     */
    private Integer buyCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserChineseName() {
        return userChineseName;
    }

    public void setUserChineseName(String userChineseName) {
        this.userChineseName = userChineseName;
    }

    public String getCargoNumber() {
        return cargoNumber;
    }

    public void setCargoNumber(String cargoNumber) {
        this.cargoNumber = cargoNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public WarehouseTypeEnum getType() {
        return type;
    }

    public void setType(WarehouseTypeEnum type) {
        this.type = type;
    }
    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }
}
