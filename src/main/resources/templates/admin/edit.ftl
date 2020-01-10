<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改用户</legend>
</fieldset>

<form class="layui-form" lay-filter="lay-form" method="get">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input name="userId" type="hidden" id="userId">
            <input name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input"
                   type="text">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input name="password" lay-verify="required" autocomplete="off" placeholder="请输入密码" class="layui-input"
                   type="text">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">中文名</label>
        <div class="layui-input-block">
            <input name="chineseName" lay-verify="required" autocomplete="off" placeholder="请输入中文名" class="layui-input"
                   type="text">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">创建时间</label>
        <div class="layui-input-block">
            <input type="text" name="createTime" id="createTime" class="layui-input dateTime" lay-verify="required">
        </div>
    </div>
    <br/><br/><br/><br/>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="lay-submit" id="lay-submit" value="修改">
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
        laydate.render({
            elem: '#createTime',
            type: 'datetime'
        });

    });

    layui.config({
        base: '${request.contextPath}/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'form'], function (obj) {
        var $ = layui.$,
            form = layui.form;

        //从主窗口取需编辑的管理员
        var data = window.parent.editUser;
        if (data != undefined) {
            //console.log(data);
            form.val("lay-form", {
                "username": data.userName,
                "password": data.passWord,
                "chineseName": data.chineseName,
                "createTime": data.createTime
            });
        }
    });
</script>
</body>
</html>