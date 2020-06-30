<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>商品入库</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>商品入库</legend>
</fieldset>

<form class="layui-form" action="insertWarehouse" method="Post">
    <div class="layui-form-item">
        <label class="layui-form-label">货物名称</label>
        <div class="layui-input-block">
            <input name="name" lay-verify="required" autocomplete="off" placeholder="请输入货物名称" class="layui-input"
                   type="text">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">货物类别</label>
        <div class="layui-input-block">
            <select name="type" lay-filter="aihao">
                <option value="0" selected="selected">请选择...</option>
                <option value="1">运动类</option>
                <option value="2">食品类</option>
                <option value="3">家具类</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">库存量</label>
        <div class="layui-input-block">
            <input name="count" autocomplete="off" class="layui-input"
                   type="text">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">入库时间</label>
        <div class="layui-input-block">
            <input type="text" name="addDate" id="addDate" class="layui-input dateTime" lay-verify="required">
        </div>
    </div>
    <br/> <br/> <br/> <br/>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1" id="btnApply">立即提交</button>
        </div>
    </div>
</form>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'laydate'], function () {
        var layer = layui.layer, form = layui.form, $ = layui.jquery, laydate = layui.laydate;
        ;
        ${message!}
        // form.on('submit(demo1)', function (data) {
        //     layer.alert(JSON.stringify(data.field), {
        //         title: '最终的提交信息'
        //     })
        //     return false;
        // });
        var end=laydate.render({
            elem: '#addDate', //
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