package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.common.SnowFlake;
import com.example.demo.model.login.LoginToken;
import com.example.demo.model.login.LoginTypeEnum;
import com.example.demo.model.mapper.LoginTokenMapper;
import com.example.demo.model.mapper.OrderMapper;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.mapper.WarehouseMapper;
import com.example.demo.model.order.Order;
import com.example.demo.model.user.User;
import com.example.demo.service.FileService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.common.security.authenticator.Login;
import org.apache.kafka.common.security.authenticator.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class AdminController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTokenMapper loginTokenMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FileService fileService;

    //转为ViewConfig全局配置类跳转12
//    @RequestMapping(value = "login", method = RequestMethod.GET)
////    public String login() {
////        return "admin/login";
////    }
    @GetMapping(value = "login")
    public ModelAndView Login(HttpSession session) {
        session.removeAttribute("loginToken");
        return new ModelAndView("admin/login");
    }

    @GetMapping(value = "reLogin")
    public ModelAndView ReLogin(HttpSession session) {
        session.removeAttribute("loginToken");
        return new ModelAndView("admin/login");
    }

    @RequestMapping(value = "index", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView index(String userName, String password, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = null;
        //登录进来后，页面url停留在index时校验令牌有无失效
        LoginToken currentLoginToken = (LoginToken) session.getAttribute("loginToken");
        if (currentLoginToken != null) {
            user=userMapper.selectById(currentLoginToken.getLoginId());
            modelAndView.addObject("userChineseName", user.getChineseName());
            modelAndView.setViewName("/admin/index");
            return modelAndView;
        }

        //登录时判断,输入用户名密码判断
        user = userMapper.selectByUserName(userName);
        if (user == null || !password.equals(user.getPassWord())) {
            modelAndView.addObject("errorMessage", "该用户不存在或者密码错误");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        }
        else {
            //有旧令牌先删除，插入新令牌,设置令牌的Session
            loginTokenMapper.deleteByLoginId(user.getId());
            LoginToken loginToken = new LoginToken(LoginTypeEnum.普通用户, user.getId(), new Date());
            loginTokenMapper.insert(loginToken);
            session.setAttribute("loginToken", loginToken);
            modelAndView.addObject("userChineseName", user.getChineseName());
            modelAndView.setViewName("/admin/index");
            return modelAndView;
        }
    }

    @GetMapping(value = "goIndex")
    public ModelAndView goIndex(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        LoginToken loginToken = (LoginToken) session.getAttribute("loginToken");
        if (loginToken == null) {
            modelAndView.addObject("errorMessage", "该用户不存在或者密码错误");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        } else {
            User user = userMapper.selectById(loginToken.getLoginId());
            modelAndView.addObject("userChineseName", user.getChineseName());
            modelAndView.setViewName("/admin/index");
            return modelAndView;
        }
    }

    @PostMapping(value = "goAddUser")
    public ModelAndView addUser() {
        return new ModelAndView("/admin/index");
    }

    @GetMapping(value = "goUpdateUser")
    public ModelAndView updateUser(HttpSession session) {
        LoginToken loginToken = (LoginToken) session.getAttribute("loginToken");
        if (loginToken == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", "请先登录。");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        } else {
            User user = userMapper.selectById(loginToken.getLoginId());
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("/admin/updateUser");
            return modelAndView;
        }
    }

    @GetMapping(value = "edit")
    public ModelAndView editUser() {
        return new ModelAndView("/admin/edit");
    }

    @GetMapping(value = "listUser")
    public ModelAndView listUser() {
        return new ModelAndView("/admin/list");
    }

    @GetMapping(value = "detail")
    public ModelAndView detail() {
        return new ModelAndView("/admin/detail");
    }

    @PostMapping(value = "insertUser")
    public ModelAndView insertUser(String username, String password, String chineseName, String createTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(createTime);
        User user = new User(username, chineseName, password, date);
        userMapper.insert(user);
        return new ModelAndView("/admin/list");
    }

    @PostMapping(value = "updateUser")
    public ModelAndView updateUser(String id, String username, String password, String chineseName, String updateTime) throws ParseException {
        User user = userMapper.selectById(Long.parseLong(id));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(updateTime);
        if (user != null) {
            user.setUserName(username);
            user.setPassWord(password);
            user.setChineseName(chineseName);
            user.setUpdateTime(date);
            userMapper.update(user);
        }

        //更新订单表中下单人员的中文名
        List<Order> orderList = orderMapper.selectByUserId(user.getId());
        for (Order order : orderList) {
            order.setUserChineseName(user.getChineseName());
            orderMapper.update(order);
        }
        //保存文件
        return new ModelAndView("/admin/list");
    }

    @PostMapping(value = "windowUpdateUser")
    @ResponseBody
    public Result windowUpdateUser(String id, String username, String password, String chineseName, String updateTime) throws ParseException {
        try {
            User user = userMapper.selectById(Long.parseLong(id));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = format.parse(updateTime);
            if (user != null) {
                user.setUserName(username);
                user.setPassWord(password);
                user.setChineseName(chineseName);
                user.setUpdateTime(date);
                userMapper.update(user);
            }

            //更新订单表中下单人员的中文名
            List<Order> orderList = orderMapper.selectByUserId(user.getId());
            for (Order order : orderList) {
                order.setUserChineseName(user.getChineseName());
                orderMapper.update(order);
            }
            return Result.success("成功");
        } catch (Exception ex) {
            return Result.error(1, ex.getMessage());
        }
    }


    @GetMapping(value = "/rest/user")
    @ResponseBody
    public HashMap getUser(HttpServletRequest request, HttpSession session) {
        LoginToken loginToken = (LoginToken) session.getAttribute("loginToken");
        HashMap<String, Object> map = new HashMap<>();
        if (loginToken == null)
            return map;
        //分页参数
        int currentIndex = NumberUtils.toInt(request.getParameter("page"), 1);
        int pageSize = NumberUtils.toInt(request.getParameter("limit"), 10);
        //0,10
        map.put("currentIndex", (currentIndex - 1) * pageSize);
        //10,20
        map.put("pageSize", currentIndex * pageSize);
        //查询条件

        String chineseName = request.getParameter("chineseName");
        if (!StringUtils.isBlank(chineseName))
            map.put("chineseName", chineseName);
        String userName = request.getParameter("userName");
        if (!StringUtils.isBlank(userName))
            map.put("userName", userName);
        List<User> list = userMapper.selectByPage(map);
        int count = userMapper.selectCount(map);

        //添加layui table分页需要的data和count参数
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", count);
        return map;
    }

    @GetMapping(value = "/rest/user/{id}")
    @ResponseBody
    public Result getUserById(@PathVariable long id) {
        User user = userMapper.selectById(id);
        if (user != null)
            return Result.sucessData(user);
        else
            return Result.error(1, "没有找到Id为" + id + "的用户信息。");
    }

    @DeleteMapping(value = "/rest/user")
    @ResponseBody
    public Result delectUserById(String id) {
        userMapper.deleteById(Long.parseLong(id));
        return Result.success("删除成功");
    }

    @DeleteMapping(value = "/rest/user/batch-delete")
    @ResponseBody
    public Result batchDeleteUser(@RequestBody String requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(requestBody);
        } catch (IOException e) {
            return Result.error(1, e.getMessage());
        }
        List<Long> idList = new ArrayList<>();
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            long id = element.get("id").asLong();
            idList.add(id);
        }
        userMapper.deleteBatch(idList);
        return Result.success("成功.");
    }

}
