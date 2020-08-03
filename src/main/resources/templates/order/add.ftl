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
    <div class="layui-form-item">
        <label class="layui-form-label">货物名称</label>
        <div class="layui-input-block">
            <input type="text" name="cargoName" readonly class="layui-input" value="${name}">
        </div>
    </div>
    <div class="layui-input-item">
        <label class="layui-form-label">购买的数量</label>
        <div class="layui-input-block">
            <input name="buyCount" lay-verify="required" id="inputBuyCount" autocomplete="off" class="layui-input" type="text" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'');calculationPrice();" onchange="calculationPrice();">
        </div>
    </div>
    <div class="layui-input-item">
        <label class="layui-form-label">单价</label>
        <div class="layui-input-block">
            <input name="price" id="inputPrice" autocomplete="off" readonly class="layui-input" type="text" value="${price}">
        </div>
    </div>
    <div class="layui-input-item">
        <label class="layui-form-label">总价</label>
        <div class="layui-input-block">
            <input name="totalPrice" lay-verify="required" readonly id="inputTotalPrice" autocomplete="off" class="layui-input" type="text">
        </div>
    </div>
    <br/>
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
    function calculationPrice() {
        var buyCount= document.getElementById("inputBuyCount").value;
        var price=document.getElementById("inputPrice").value;
        console.log(parseFloat(price));
        var totalPrice=parseInt(buyCount) * parseFloat(price);
        if(isNaN(totalPrice))
            totalPrice=0;
        document.getElementById("inputTotalPrice").value=totalPrice;
    }
</script>
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
        var end=laydate.render({
            elem: '#orderDate', //
            type: 'datetime',
            done: function (value, date) {
                endMax = end.config.max;
                end.config.min = date;
                end.config.min.month = date.month - 1;
            },
            change: function (value, date, endDate) {
                var timestamp2 = Date.parse(new Date(value));
                timestamp2 = timestamp2 / 1000;
                end.config.min = timestamp2;
                end.config.min.month = date.month - 1;
            }
       });

    });
</script>
</body>
</html>