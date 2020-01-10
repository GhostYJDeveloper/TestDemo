package com.example.demo.model.mapper;

import com.example.demo.model.warehouse.Warehouse;
import com.example.demo.model.warehouse.WarehouseTypeEnum;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface WarehouseMapper {
    /**
     * 插入
     * @param warehouse
     * @return
     */
    int insert(Warehouse warehouse);

    /**
     * 删除
     * @param id
     */
    void deleteById(long id);

    /**
     * 批量删除
     * @param idList
     */
    void deleteBatch(List<Long> idList);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Warehouse selectById(long id);

    /**
     * 根据货物类别查询
     * @param type
     * @return
     */
    List<Warehouse> selectByType(WarehouseTypeEnum type);

    /**
     * 根据货物编号查询
     * @param number
     * @return
     */
    Warehouse selectByNumber(String number);

    /**
     * 分页查询
     * @param  paramMap
     * @return
     */
    List<Warehouse> selectByPage(Map paramMap);

    /**
     * 根据货物编号批量查询
     * @param numberList
     * @return
     */
    List<Warehouse> selectByNumberList(List<String> numberList);

    /**
     * 查询数量
     * @return
     */
    int selectCount();

    /**
     * 更新
     * @param warehouse
     */
    void update(Warehouse warehouse);
}
