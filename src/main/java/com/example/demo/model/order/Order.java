package com.example.demo.model.order;

import com.example.demo.common.SnowFlake;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单类
 */
public class Order {
    public Order(){

    }

    public Order(long userId,String userChineseName,String cargoNumber,String cargoName,String orderNumber,
                 Date orderDate,Integer buyCount
    ,BigDecimal price,BigDecimal totalPrice){
        id= SnowFlake.instant().nextId();
        this.userId=userId;
        this.userChineseName=userChineseName;
        this.cargoNumber=cargoNumber;
        this.orderNumber=orderNumber;
        this.orderDate=orderDate;
        this.buyCount=buyCount;
        this.cargoName=cargoName;
        this.price=price;
        this.totalPrice=totalPrice;
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
     * 购买数量
     */
    private Integer buyCount;

    /**
     * 货物单价
     */
    private BigDecimal price;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

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
    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }
    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
