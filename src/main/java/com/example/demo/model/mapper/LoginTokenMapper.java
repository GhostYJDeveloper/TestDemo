package com.example.demo.model.mapper;

import com.example.demo.model.login.LoginToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface LoginTokenMapper {
    /**
     * 插入
     * @param loginToken
     * @return
     */
    int insert(LoginToken loginToken);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    LoginToken selectById(long id);

    /**
     * 根据登录人员Id查询
     * @param loginId
     * @return
     */
    LoginToken selectByLoginId(long loginId);

    /**
     * 查询数量
     * @return
     */
    int selectCount();

    /**
     * 删除
     * @param id
     */
    int deleteById(long id);
}
