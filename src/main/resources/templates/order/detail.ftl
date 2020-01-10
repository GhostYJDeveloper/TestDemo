<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>订单详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<form class="layui-form" lay-filter="lay-form" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">订单编号</label>
            <div class="layui-input-inline">
                <input type="text" name="orderNumber" readonly class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">下单用户名称</label>
            <div class="layui-input-inline">
                <input type="text" name="userChineseName" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">货物编号</label>
            <div class="layui-input-inline">
                <input type="text" name="cargoNumber" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">下单日期</label>
            <div class="layui-input-inline">
                <input type="text" name="orderDate" readonly class="layui-input">
            </div>
        </div>
    </div>
</form>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${request.contextPath}/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'form'], function () {
        var $ = layui.$,
            form = layui.form;

        //从主窗口取需查看的用户
        var data = window.parent.detailUser;
        if (data != undefined) {
            form.val("lay-form", {
                "orderNumber": data.orderNumber,
                "userChineseName": data.userChineseName,
                "cargoNumber": data.cargoNumber,
                "orderDate": data.orderDate,
            });
        }
    });
</script>

</body>