package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.domain.mapper.UserMapper;
import com.example.demo.domain.user.User;
import com.example.demo.model.login.LoginToken;
import com.example.demo.model.mapper.LoginTokenMapper;
import com.example.demo.model.mapper.OrderMapper;
import com.example.demo.model.mapper.WarehouseMapper;
import com.example.demo.model.order.Order;
import com.example.demo.model.order.OrderConfig;
import com.example.demo.model.warehouse.CreateNumber;
import com.example.demo.model.warehouse.Warehouse;
import com.example.demo.model.warehouse.WarehouseTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.eclipse.jetty.client.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Transactional(rollbackFor = Exception.class)
public class OrderController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    WarehouseMapper warehouseMapper;
    @Autowired
    LoginTokenMapper loginTokenMapper;

    @RequestMapping(value = "/order/add/{id}", method = RequestMethod.GET)
    public ModelAndView gotoAdd(@PathVariable String id, HttpSession session) {
        LoginToken loginToken = (LoginToken) session.getAttribute("loginToken");
        User user = null;
        Warehouse warehouse = null;
        if (loginToken != null)
            user = userMapper.selectById(loginToken.getLoginId());
        if(id != null)
            warehouse = warehouseMapper.selectById(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("number", warehouse.getNumber());
        modelAndView.addObject("name", warehouse.getName());
        if (user != null) {
            modelAndView.addObject("chineseName", user.getChineseName());
            modelAndView.addObject("userId", String.valueOf(user.getId()));
        }
        modelAndView.setViewName("/order/add");
        return modelAndView;
    }

    @RequestMapping(value = "/order/detail", method = RequestMethod.GET)
    public ModelAndView gotoDetail() {
        return new ModelAndView("/order/detail");
    }

    @RequestMapping(value = "/order/list", method = RequestMethod.GET)
    public ModelAndView gotoList(HttpSession session) {
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
        return new ModelAndView("/order/list");
    }

    @RequestMapping(value = "/order/insertOrder", method = RequestMethod.POST)
    @Transactional
    public ModelAndView insertOrder(String userId, String userChineseName, String cargoNumber,String cargoName, String orderDate
            , String buyCount) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(orderDate);
        //生成编号
        String orderNumber = CreateNumber.make();
        Order order = new Order(Long.parseLong(userId), userChineseName, cargoNumber,cargoName, orderNumber, date, Integer.parseInt(buyCount));
        orderMapper.insert(order);

        //减库存
        Warehouse warehouse = warehouseMapper.selectByNumber(cargoNumber);
        int count = warehouse.getCount() - Integer.parseInt(buyCount);
        warehouse.setCount(count);
        warehouseMapper.update(warehouse);
        return new ModelAndView("/warehouse/list");
    }

    @RequestMapping(value = "/rest/order", method = RequestMethod.GET)
    @ResponseBody
    public HashMap getOrder(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap();
        //分页参数
        int currentIndex = NumberUtils.toInt(request.getParameter("page"), 1);
        int pageSize = NumberUtils.toInt(request.getParameter("limit"), 10);
        //0,10
        map.put("currentIndex", (currentIndex - 1) * pageSize);
        //10,20
        map.put("pageSize", currentIndex * pageSize);

        //查询条件
        String number = request.getParameter("number");
        if (!StringUtils.isBlank(number))
            map.put("number", number);

        List<Order> list = orderMapper.selectByPage(map);
        int count = orderMapper.selectCount();

        List<Warehouse> warehouseList = warehouseMapper.selectByNumberList(
                list.stream().map(Order::getCargoNumber).collect(Collectors.toList()));
        List<OrderConfig> orderConfigList = new ArrayList<>();
        for (Order item : list) {
            //取满足编号的第一个仓库数据
            Optional<Warehouse> warehouseOptional = warehouseList.stream()
                    .filter(s -> s.getNumber().equals(item.getCargoNumber())).findFirst();
            if (warehouseOptional.isPresent()) {
                Warehouse warehouse = warehouseOptional.get();
                orderConfigList.add(new OrderConfig(item, warehouse.getName(), warehouse.getType()));
            }
        }

        //添加layui table分页需要的data和count参数
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", orderConfigList);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/rest/orderDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result detail(String id, String userChineseName, String typeName) {
        Order order = orderMapper.selectById(Long.parseLong(id));
        OrderConfig orderConfig = new OrderConfig(order, userChineseName, WarehouseTypeEnum.getWarehouseType(typeName));
        if (orderConfig != null)
            return Result.sucessData(orderConfig);
        else
            return Result.error(1, "没有找到id为" + id + "的订单信息");
    }

    @RequestMapping(value = "/rest/order", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result deleteById(String id) {
        Order order = orderMapper.selectById(Long.parseLong(id));

        //加库存
        Warehouse warehouse = warehouseMapper.selectByNumber(order.getCargoNumber());
        warehouse.setCount(warehouse.getCount() + order.getBuyCount());
        warehouseMapper.update(warehouse);

        int flag = orderMapper.deleteById(Long.parseLong(id));
        if (flag > 0)
            return Result.success("删除成功");
        else
            return Result.error(1, "删除失败");
    }

    @RequestMapping(value = "/rest/order/batch-delete", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional
    public Result branchDelete(@RequestBody String requestBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        jsonNode = objectMapper.readTree(requestBody);
        List<Long> idList = new ArrayList<>();
        Iterator<JsonNode> el = jsonNode.elements();
        while (el.hasNext()) {
            JsonNode element = el.next();
            Long id = element.get("id").asLong();
            idList.add(id);

            //加库存
            Order order=orderMapper.selectById(id);
            Warehouse warehouse = warehouseMapper.selectByNumber(order.getCargoNumber());
            warehouse.setCount(warehouse.getCount() + order.getBuyCount());
            warehouseMapper.update(warehouse);
        }
        orderMapper.deleteBatch(idList);
        return Result.success("批量删除成功");
    }
}
