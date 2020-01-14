<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改个人信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改个人信息</legend>
</fieldset>

<form class="layui-form" action="/updateUser" lay-filter="lay-form" method="post">
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
                   type="password">
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
    layui.config({
        base: '${request.contextPath}/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'table'], function () {
        var $ = layui.$,
            form = layui.form,
            table = layui.table;
        table.render({
            elem: '#lay-table',
            url: layui.setter.contextPath + '/rest/user?id=',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'Id', align: 'center'},
                {field: 'chineseName', title: '中文名', align: 'center'},
                {field: 'userName', title: '用户名', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {title: '操作', align: 'center', width: 180, fixed: 'right', toolbar: '#table-action'}
            ]],
            text: {none: '无数据'},
            page: true,
            loading: true
        });
        laydate.render({
            elem: '#createTime',
            type: 'datetime'
        });
    });
</script>

</body>
</html>