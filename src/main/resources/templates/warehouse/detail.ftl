<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>仓库详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<form class="layui-form" lay-filter="lay-form" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">货物名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" readonly class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">货物类别</label>
            <div class="layui-input-inline">
                <input type="text" name="type" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">库存量</label>
            <div class="layui-input-inline">
                <input type="text" name="count" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">入库时间</label>
            <div class="layui-input-inline">
                <input type="text" name="addDate" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出库时间</label>
            <div class="layui-input-inline">
                <input type="text" name="outDate" readonly class="layui-input">
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
            var typeName=trunEnum(data.type);
            form.val("lay-form", {
                "name": data.name,
                "type": typeName,
                "count": data.count,
                "addDate": data.addDate,
                "outDate": data.outDate
            });
        }
    });
    //转换枚举
    function trunEnum(type) {
        if(type=="SUPPORT")
            return "运动类";
        else if(type=="FOOD")
            return "食品类";
        else if(type=="FURNITURE")
            return "家具类";
    }
</script>

</body>