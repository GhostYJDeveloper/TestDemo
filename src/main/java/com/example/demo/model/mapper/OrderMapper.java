package com.example.demo.model.mapper;

import com.example.demo.model.order.Order;
import com.example.demo.model.warehouse.Warehouse;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Mapper
@Transactional(rollbackFor = Exception.class)
public interface OrderMapper {
    /**
     * 插入
     * @param order
     * @return
     */
    int insert(Order order);

    /**
     * 更新
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 删除
     * @param id
     */
    int deleteById(long id);

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
    Order selectById(long id);

    /**
     * 分页查询
     * @param  paramMap
     * @return
     */
    List<Order> selectByPage(Map paramMap);

    /**
     * 查询数量
     * @return
     */
    int selectCount();

    /**
     * 根据下单用户Id查询
     * @param  userId
     * @return
     */
    List<Order> selectByUserId(long userId);
}
