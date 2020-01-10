<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<form class="layui-form" lay-filter="lay-form" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" readonly class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="text" name="passWord" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">中文名</label>
            <div class="layui-input-inline">
                <input type="text" name="chineseName" readonly class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">创建时间</label>
            <div class="layui-input-inline">
                <input type="text" name="createTime" readonly class="layui-input">
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
            //console.log(data);
            form.val("lay-form", {
                "userName": data.userName,
                "passWord": data.passWord,
                "chineseName": data.chineseName,
                "createTime": data.createTime
            });
        }

        /*$.ajax({
            url: layui.setter.contextPath + '/rest/user/' + common.getQueryString('id'),
            type: 'GET',
            dataType: 'JSON',
            success: function (data) {
                if (data.success) {
                    var user = data.data;
                    form.val("lay-form", {
                        })
                }
                else {
                    layer.msg(data.message);
                }
            }
        });*/
    });
</script>

</body>