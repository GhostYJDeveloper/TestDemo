package com.example.demo.model.warehouse;

import com.example.demo.common.SnowFlake;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.kafka.connect.data.Decimal;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 仓库
 */
public class Warehouse {

    public Warehouse(){

    }

    public Warehouse(String number,String name,Integer type,Integer count
    ,Date addDate,BigDecimal price){
        this.id= SnowFlake.instant().nextId();
        this.number=number;
        this.name=name;
        this.type=WarehouseTypeEnum.getWarehouseType(type);
        this.count=count;
        this.addDate=addDate;
        this.price=price;
    }

    /**
     * 从仓储还原
     * @param id
     * @param number
     * @param name
     * @param type
     * @param count
     * @param addDate
     * @param outDate
     */
    public Warehouse(long id,String number,String name,WarehouseTypeEnum type,Integer count
            ,Date addDate,Date outDate,BigDecimal price){
        this.id= id;
        this.number=number;
        this.name=name;
        this.type=type;
        this.count=count;
        this.addDate=addDate;
        this.outDate=outDate;
        this.price=price;
    }

    /**
     * 仓库Id
     */
    private long id;

    /**
     * 货物编号
     */
    private String number;

    /**
     * 货物名称
     */
    private String name;

    /**
     * 货物类别
     */
    private WarehouseTypeEnum type;

    /**
     * 库存量
     */
    private int count;

    /**
     * 入库时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addDate;

    /**
     * 出库时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outDate;

    /**
     * 货物单价
     */
    private BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WarehouseTypeEnum getType() {
        return type;
    }

    public void setType(WarehouseTypeEnum type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

