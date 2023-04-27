package com.example.demo.controller;

import com.example.demo.common.LoginTokenHelper;
import com.example.demo.common.Result;
import com.example.demo.model.login.LoginToken;
import com.example.demo.model.mapper.LoginTokenMapper;
import com.example.demo.model.mapper.OrderMapper;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.mapper.WarehouseMapper;
import com.example.demo.model.order.Order;
import com.example.demo.model.order.OrderConfig;
import com.example.demo.model.user.User;
import com.example.demo.model.warehouse.CreateNumber;
import com.example.demo.model.warehouse.Warehouse;
import com.example.demo.model.warehouse.WarehouseTypeEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
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

    @GetMapping(value = "/order/add/{id}")
    public ModelAndView gotoAdd(@PathVariable String id, HttpSession session) {
        LoginToken loginToken = (LoginToken) session.getAttribute("loginToken");
        User user = null;
        Warehouse warehouse = null;
        if (loginToken != null)
            user = userMapper.selectById(loginToken.getLoginId());
        if (id != null)
            warehouse = warehouseMapper.selectById(Long.parseLong(id));
        DecimalFormat df = new DecimalFormat("0.00");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("number", warehouse.getNumber());
        modelAndView.addObject("name", warehouse.getName());
        modelAndView.addObject("price", df.format(warehouse.getPrice()));
        if (user != null) {
            modelAndView.addObject("chineseName", user.getChineseName());
            modelAndView.addObject("userId", String.valueOf(user.getId()));
        }
        modelAndView.setViewName("/order/add");
        return modelAndView;
    }

    @GetMapping(value = "/order/detail")
    public ModelAndView gotoDetail() {
        return new ModelAndView("/order/detail");
    }

    @GetMapping(value = "/order/list")
    public ModelAndView gotoList(HttpSession session) {
        //验证登录令牌
        if (!LoginTokenHelper.isLoginTokenDisabled(session)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", "该用户的令牌不存在,请先登录。");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        }
        return new ModelAndView("/order/list");
    }

    @PostMapping(value = "/order/insertOrder")
    @Transactional
    public ModelAndView insertOrder(String userId, String userChineseName, String cargoNumber, String cargoName
            , String buyCount, String price, String totalPrice) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        //判断当前用户余额 够不够下单
        User user = userMapper.selectById(Long.parseLong(userId));
        if (user == null) {
            modelAndView.addObject("errorMessage", "该用户的令牌不存在,请先登录。");
            modelAndView.setViewName("/admin/error");
            return modelAndView;
        } else {
            if (user.getMoney().compareTo(new BigDecimal(totalPrice)) == -1) {
                modelAndView.addObject("errorMessage", "余额不足，请充值。");
                modelAndView.setViewName("/admin/error");
                return modelAndView;
            }
        }
        //生成编号
        String orderNumber = CreateNumber.make();
        Date date=new Date(System.currentTimeMillis());
        Order order = new Order(Long.parseLong(userId), userChineseName, cargoNumber, cargoName, orderNumber,
                date, Integer.parseInt(buyCount),
                new BigDecimal(price), new BigDecimal(totalPrice));
        orderMapper.insert(order);

        //减库存
        Warehouse warehouse = warehouseMapper.selectByNumber(cargoNumber);
        int count = warehouse.getCount() - Integer.parseInt(buyCount);
        warehouse.setCount(count);
        warehouseMapper.update(warehouse);

        //扣除用户的钱
        user.setMoney(user.getMoney().subtract(new BigDecimal(totalPrice)));
        userMapper.update(user);

        modelAndView.setViewName("redirect:/index");
        return modelAndView;
    }

    @GetMapping(value = "/rest/order")
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
        String orderNumber = request.getParameter("orderNumber");
        if (!StringUtils.isBlank(orderNumber))
            map.put("orderNumber", orderNumber);

        List<Order> list = orderMapper.selectByPage(map);
        int count = orderMapper.selectCount();

        List<OrderConfig> orderConfigList = new ArrayList<>();
        if(!list.isEmpty()) {
            List<Warehouse> warehouseList = warehouseMapper.selectByNumberList(
                    list.stream().map(Order::getCargoNumber).collect(Collectors.toList()));
            for (Order item : list) {
                //取满足编号的第一个仓库数据
                Optional<Warehouse> warehouseOptional = warehouseList.stream()
                        .filter(s -> s.getNumber().equals(item.getCargoNumber())).findFirst();
                if (warehouseOptional.isPresent()) {
                    Warehouse warehouse = warehouseOptional.get();
                    orderConfigList.add(new OrderConfig(item, warehouse.getName(), warehouse.getType()));
                }
            }
        }

        //添加layui table分页需要的data和count参数
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", orderConfigList);
        map.put("count", count);
        return map;
    }

    @GetMapping(value = "/rest/orderDetail")
    @ResponseBody
    public Result detail(String id, String userChineseName, String typeName) {
        Order order = orderMapper.selectById(Long.parseLong(id));
        OrderConfig orderConfig = new OrderConfig(order, userChineseName, WarehouseTypeEnum.getWarehouseType(typeName));
        if (orderConfig != null)
            return Result.sucessData(orderConfig);
        else
            return Result.error(1, "没有找到id为" + id + "的订单信息");
    }

    @DeleteMapping(value = "/rest/order")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Result deleteById(String id) {
        Order order = orderMapper.selectById(Long.parseLong(id));

        //返回用户的金额
        User user=userMapper.selectById(order.getUserId());
        user.setMoney(user.getMoney().add(order.getTotalPrice()));
        userMapper.update(user);

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

    @DeleteMapping(value = "/rest/order/batch-delete")
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
            Order order = orderMapper.selectById(id);
            Warehouse warehouse = warehouseMapper.selectByNumber(order.getCargoNumber());
            warehouse.setCount(warehouse.getCount() + order.getBuyCount());
            warehouseMapper.update(warehouse);
        }
        orderMapper.deleteBatch(idList);
        return Result.success("批量删除成功");
    }
}
