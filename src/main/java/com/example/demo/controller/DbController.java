package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserConfig;
import com.example.demo.domain.user.UserConfigService;
import com.example.demo.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DbController {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserConfigService userConfigService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/Test")
    public String Test() {

        UserConfig userConfig = userConfigService.selectByChineseName("阿浩");
        User user = new User(userConfig.getUserName(), userConfig.getChineseName(),
                userConfig.getPassword(), userConfig.getCreateTime());
        return user.getChineseName();
    }


    @RequestMapping(value = "user/test", method = RequestMethod.GET)
    public User TestUser(@RequestParam("id") long id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @RequestMapping(value = "user/userlistTest",method = RequestMethod.GET)
    public List<User> TestUserList() {
        List<User> userList = userMapper.findAll();
        return userList;
    }
}
