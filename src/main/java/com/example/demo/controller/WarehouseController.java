package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.model.login.LoginToken;
import com.example.demo.model.mapper.LoginTokenMapper;
import com.example.demo.model.warehouse.CreateNumber;
import com.example.demo.model.warehouse.Warehouse;
import com.example.demo.model.mapper.WarehouseMapper;

import com.example.demo.model.warehouse.WarehouseTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
public class WarehouseController {
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private LoginTokenMapper loginTokenMapper;

    @RequestMapping(value = "/warehouse/manageWarehouse", method = RequestMethod.GET)
    public String manageWarehouse() {
        return "/warehouse/add";
    }

    @RequestMapping(value = "/warehouse/insertWarehouse", method = RequestMethod.POST)
    @Transactional
    public ModelAndView insertWarehouse(String name, String type, String count, String addDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(addDate);
        //生成编号
        String number= CreateNumber.make();
        Warehouse warehouse = new Warehouse(number, name,Integer.parseInt(type),
                Integer.parseInt(count), date);
        warehouseMapper.insert(warehouse);
        return new ModelAndView("/warehouse/list");
    }

    @RequestMapping(value = "/warehouse/add")
    public ModelAndView gotoAdd(){
        return new ModelAndView("/warehouse/add");
    }

    @RequestMapping(value = "/warehouse/list",method = RequestMethod.GET)
    public ModelAndView gotoList(HttpSession session){
        //先通过Session判断令牌有无失效
        LoginToken loginToken = (LoginToken) session.getAttribute("loginToken");
        if (loginToken == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", "令牌失效,请先登录。");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        }
        //如果Session没有失效，再去表里查看令牌有没有被删除
        else {
            loginToken = loginTokenMapper.selectById(loginToken.getId());
            if (loginToken == null) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("errorMessage", "该用户的令牌不存在,请先登录。");
                modelAndView.setViewName("/admin/error");
                return modelAndView;
            }
        }
        return new ModelAndView("/warehouse/list");
    }

    @RequestMapping(value = "/warehouse/detail",method = RequestMethod.GET)
    public ModelAndView gotoDetail(){
        return new ModelAndView("/warehouse/detail");
    }

    @RequestMapping(value = "/rest/warehouse", method = RequestMethod.GET)
    @ResponseBody
    public HashMap getWarehouse(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap();
        //分页参数
        int currentIndex = NumberUtils.toInt(request.getParameter("page"), 1);
        int pageSize = NumberUtils.toInt(request.getParameter("limit"), 10);
        //0,10
        map.put("currentIndex",(currentIndex - 1) * pageSize);
        //10,20
        map.put("pageSize",currentIndex * pageSize);

        //查询条件
        String name = request.getParameter("name");
        if (!StringUtils.isBlank(name))
            map.put("name", name);
        String type = request.getParameter("type");
        if (!StringUtils.isBlank(type))
            map.put("type", Integer.parseInt(type));

        List<Warehouse> list = warehouseMapper.selectByPage(map);
        int count = warehouseMapper.selectCount();

        //添加layui table分页需要的data和count参数
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/rest/warehouse/{id}",method = RequestMethod.GET)
    public @ResponseBody Result selectById(@PathVariable Long id){
        Warehouse warehouse= warehouseMapper.selectById(id);
        if (warehouse != null)
            return Result.sucessData(warehouse);
        else
            return Result.error(1, "没有找到Id为" + id + "的用户信息。");
    }

    @RequestMapping(value = "/rest/warehouse",method = RequestMethod.DELETE)
    public @ResponseBody Result deleteById(Long id){
        warehouseMapper.deleteById(id);
        return Result.success();
    }

    @RequestMapping(value = "/rest/warehouse/batch-delete",method = RequestMethod.DELETE)
    @Transactional
    public @ResponseBody Result batchDeleteById(@RequestBody String requestBody) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=null;
        jsonNode=objectMapper.readTree(requestBody);
        List<Long> idList=new ArrayList<>();
        Iterator<JsonNode> el=jsonNode.elements();
        while (el.hasNext()){
            JsonNode element=el.next();
            Long id=element.get("id").asLong();
            idList.add(id);
        }
        warehouseMapper.deleteBatch(idList);
        return Result.success();
    }
}
