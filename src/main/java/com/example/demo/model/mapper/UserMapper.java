package com.example.demo.model.mapper;
import com.example.demo.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 以注解方式结合MyBatis查询数据
 */
@Mapper
@Service
public interface UserMapper {
    /**
     * 根据id查询
     */
    @Select("select * from t_user where u_id=#{id}")
    @Results({
            @Result(property = "id", column = "u_id"),
            @Result(property = "userName", column = "u_userName"),
            @Result(property = "chineseName", column = "u_chineseName"),
            @Result(property = "passWord", column = "u_passWord"),
            @Result(property = "createTime", column = "u_createTime")
    })
    User selectById(long id);

    /**
     * 插入
     * @param user
     */
    int insert(User user);

    /**
     * 更新
     * @param user
     */
    int update(User user);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(long id);

    /**
     * 批量删除
     * @param idList
     */
    void deleteBatch(List<Long> idList);

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据条件分页查询
     * @param paramMap
     * @return
     */
    List<User> selectByPage(Map paramMap);

    /**
     * 根据条件查找总数
     * @param paramMap
     * @return
     */
    int selectCount(Map paramMap);

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    User selectByUserName(String userName);
}
