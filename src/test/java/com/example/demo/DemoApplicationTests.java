package com.example.demo;

import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.user.User;
import com.example.demo.model.user.UserConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserConfigService userConfigService;
    @Autowired
    private UserMapper userMapper;


    @Test
    public void contextLoads() {
        String sql = "select * from t_user ";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
        System.out.println(user.getChineseName());
    }

    @Test
    public void TestUser() {
        User user = userMapper.selectById(254239560869871616L);
        System.out.println(user.getUserName());
    }

    @Test
    public void TestUserList() {
        List<User> userList = userMapper.findAll();
        System.out.println(userList.get(0).getUserName());
    }

}
