<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户下单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>用户下单</legend>
</fieldset>

<form class="layui-form" action="${request.contextPath}/order/insertOrder" method="Post">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="hidden" value="${userId}" name="userId">
            <input type="text" name="userChineseName" readonly class="layui-input" value="${chineseName}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">货物编号</label>
        <div class="layui-input-block">
            <input type="text" name="cargoNumber" readonly class="layui-input" value="${number}">
        </div>
    </div>
    <div class="layui-input-item">
        <label class="layui-form-label">购买的数量</label>
        <div class="layui-input-block">
            <input name="buyCount" autocomplete="off" class="layui-input" type="text">
        </div>
    </div>
    <br/>
    <div class="layui-form-item">
        <label class="layui-form-label">下单日期</label>
        <div class="layui-input-block">
            <input type="text" name="orderDate" id="orderDate" class="layui-input dateTime" lay-verify="required">
        </div>
    </div>
    <br/> <br/> <br/> <br/>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1" id="btnApply">下单</button>
        </div>
    </div>
</form>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'laydate'], function () {
        var layer = layui.layer, form = layui.form, $ = layui.jquery, laydate = layui.laydate;
        ${message!}
        // form.on('submit(demo1)', function (data) {
        //     layer.alert(JSON.stringify(data.field), {
        //         title: '最终的提交信息'
        //     })
        //     return false;
        // });
        laydate.render({
            elem: '#orderDate',
            type: 'datetime'
        });

    });
</script>
</body>
</html>