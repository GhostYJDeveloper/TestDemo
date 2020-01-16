<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GhostYJ SMM框架登录</title>
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
    <style type="text/css">
        .login {
            height: 160px;
            width: 260px;
            padding: 20px;

            border-radius: 4px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin: -150px 0 0 -150px;
            z-index: 99;
        }

        .login h1 {
            text-align: center;
            color: #fff;
            font-size: 20px;
            margin-bottom: 20px;
        }

        .login_btn {
            width: 35%;
            float: left;
        }

        .register_btn {
            width: 35%;
            float: right;
        }
    </style>
</head>
<body>
<div class="login layui-bg-cyan" style="width: 300px;height: 220px;">
    <form id="form" name="loginForm" action="" method="post">
    <h1>活动管理后台</h1>
    <div class="layui-bg-blue"></div>

    <div class="layui-form-item">
        <input class="layui-input" name="userName" placeholder="用户名" lay-verify="required" type="text"
               autocomplete="off">
    </div>
    <div class="layui-form-item">
        <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password"
               autocomplete="off">
    </div>
    <button class="layui-btn login_btn" id="btnLogin">登录</button>
    <button class="layui-btn register_btn" id="btnRegister">注册</button>
        </form>
</div>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer'], function () {
        var layer = layui.layer, form = layui.form, $ = layui.jquery;
        ${message!}
    });


    $('#btnLogin').on('click', function () {
        document.loginForm.action = '${request.contextPath}/index';
    });

    $('#btnRegister').on('click', function () {
        document.loginForm.action = '${request.contextPath}/goAddUser';
    });
</script>
</body>
</html>