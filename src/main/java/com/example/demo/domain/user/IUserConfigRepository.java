package com.example.demo.domain.user;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.HashMap;
import java.util.List;

public interface IUserConfigRepository {
    /**
     *
     * 根据中文名查询
     */
    UserConfig selectByChineseName(String chineseName);

    /**
     *
     * 根据中文名查询
     */
    User selectByChineseName1(String chineseName);


    /**
     * 插入
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param criteria
     * @return
     */
    List<User> selectByPage(int page, int limit, HashMap<String, Object> criteria);

    /**
     * 查询数量
     * @param criteria
     * @return
     */
    int selectCount(HashMap<String, Object> criteria);

    /**
     * 转换用户
     * @param sqlRowSet
     * @return
     */
    User convert(SqlRowSet sqlRowSet);

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    User selectUserById(Long id);

    /**
     * 根据Id删除用户
     * @param id
     */
    void deleteUserById(Long id);

    /**
     * 根据Id集合批量删除用户
     * @param idList
     */
    void deleteUserByIdList(List<Long> idList);
}
