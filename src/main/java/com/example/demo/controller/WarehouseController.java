package com.example.demo.controller;

import com.example.demo.common.LoginTokenHelper;
import com.example.demo.common.Result;
import com.example.demo.model.login.LoginToken;
import com.example.demo.model.mapper.LoginTokenMapper;
import com.example.demo.model.warehouse.CreateNumber;
import com.example.demo.model.warehouse.Warehouse;
import com.example.demo.model.mapper.WarehouseMapper;

import com.example.demo.model.warehouse.WarehouseTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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

    @GetMapping(value = "/warehouse/manageWarehouse")
    public String manageWarehouse() {
        return "/warehouse/add";
    }

    @PostMapping(value = "/warehouse/insertWarehouse")
    @Transactional
    public ModelAndView insertWarehouse(String name, String type, String count, String addDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date date = format.parse(addDate);
        //生成编号
        String number= CreateNumber.make();
        Warehouse warehouse = new Warehouse(number, name,Integer.parseInt(type),
                Integer.parseInt(count), date);
        warehouseMapper.insert(warehouse);
        return new ModelAndView("/warehouse/list");
    }

    @GetMapping(value = "/warehouse/add")
    public ModelAndView gotoAdd(HttpSession session){
        //验证登录令牌
        if(!LoginTokenHelper.isLoginTokenDisabled(session)){
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.addObject("errorMessage", "该用户的令牌不存在,请先登录。");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        }
        return new ModelAndView("/warehouse/add");
    }

    @GetMapping(value = "/warehouse/list")
    public ModelAndView gotoList(HttpSession session){
        //验证登录令牌
        if(!LoginTokenHelper.isLoginTokenDisabled(session)){
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.addObject("errorMessage", "该用户的令牌不存在,请先登录。");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        }
        return new ModelAndView("/warehouse/list");
    }



    @GetMapping(value = "/warehouse/detail")
    public ModelAndView gotoDetail(){
        return new ModelAndView("/warehouse/detail");
    }

    @GetMapping(value = "/rest/warehouse")
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

    @GetMapping(value = "/rest/warehouse/{id}")
    public @ResponseBody Result selectById(@PathVariable Long id){
        Warehouse warehouse= warehouseMapper.selectById(id);
        if (warehouse != null)
            return Result.sucessData(warehouse);
        else
            return Result.error(1, "没有找到Id为" + id + "的用户信息。");
    }

    @DeleteMapping(value = "/rest/warehouse")
    public @ResponseBody Result deleteById(Long id){
        warehouseMapper.deleteById(id);
        return Result.success();
    }

    @DeleteMapping(value = "/rest/warehouse/batch-delete")
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
