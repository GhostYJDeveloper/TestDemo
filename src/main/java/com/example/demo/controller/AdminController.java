package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.domain.mapper.UserMapper;
import com.example.demo.domain.user.User;
import com.example.demo.model.login.LoginToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.common.security.authenticator.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private UserMapper userMapper;


    //转为ViewConfig全局配置类跳转12
//    @RequestMapping(value = "login", method = RequestMethod.GET)
////    public String login() {
////        return "admin/login";
////    }

    @RequestMapping(value = "reLogin",method = RequestMethod.GET)
    public ModelAndView ReLogin(HttpSession session){
        session.removeAttribute("loginToken");
        return new ModelAndView("admin/login");
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(String userName, String password, HttpSession session) {
        User user = userMapper.selectByUserName(userName);
        if (user == null || !password.equals(user.getPassWord())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", "该用户不存在或者密码错误");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        }
        session.setAttribute("loginToken", new LoginToken("User", String.valueOf(user.getId())));
        return new ModelAndView("/admin/index");
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public ModelAndView addUser() {
        return new ModelAndView("/admin/addUser");
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.GET)
    public ModelAndView updateUser(HttpSession session) {
        LoginToken loginToken=(LoginToken)session.getAttribute("loginToken");
        if(loginToken==null)
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", "请先登录。");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        }
        return new ModelAndView("/admin/updateUser?id="+loginToken.getLoginId());
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView editUser() {
        return new ModelAndView("/admin/edit");
    }

    @RequestMapping(value = "listUser", method = RequestMethod.GET)
    public ModelAndView listUser() {
        return new ModelAndView("/admin/list");
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public ModelAndView detail() {
        return new ModelAndView("/admin/detail");
    }

    @RequestMapping(value = "insertUser", method = RequestMethod.GET)
    public ModelAndView insertUser(String username, String password, String chineseName, String createTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(createTime);
        User user = new User(username, chineseName, password, date);
        userMapper.insert(user);
        return new ModelAndView("/admin/list");
    }

    @RequestMapping(value = "updateUser/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Result updateUser(@PathVariable String id, String username, String password, String chineseName, String createTime) throws ParseException {
        User user = userMapper.selectById(Long.parseLong(id));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(createTime);
        if (user != null) {
            user.setUserName(username);
            user.setPassWord(password);
            user.setChineseName(chineseName);
            user.setCreateTime(date);
            int flag = userMapper.update(user);
            if (flag > 0)
                return Result.success("更新成功.");
            else
                return Result.error(1, "更新失败.");
        }
        return Result.error(1, "更新失败.");
    }


    @RequestMapping(value = "/rest/user", method = RequestMethod.GET)
    @ResponseBody
    public HashMap getUser(HttpServletRequest request, HttpSession session) {
        LoginToken loginToken=(LoginToken)session.getAttribute("loginToken");
        HashMap<String, Object> map = new HashMap<>();
        if(loginToken==null)
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

    @RequestMapping(value = "/rest/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserById(@PathVariable long id) {
        User user = userMapper.selectById(id);
        if (user != null)
            return Result.sucessData(user);
        else
            return Result.error(1, "没有找到Id为" + id + "的用户信息。");
    }

    @RequestMapping(value = "/rest/user", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delectUserById(String id) {
        userMapper.deleteById(Long.parseLong(id));
        return Result.success("删除成功");
    }

    @RequestMapping(value = "/rest/user/batch-delete")
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
