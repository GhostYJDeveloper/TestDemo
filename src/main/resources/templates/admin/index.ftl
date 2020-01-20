<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>SMM首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<style>
    body {
        margin: 0;
        padding: 0;
    }
</style>

<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">GhostYJ SMM框架</div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item"><a href="">控制台</a></li>
                <li class="layui-nav-item"><a href="">商品管理</a></li>
                <li class="layui-nav-item"><a id="aUser" href="javascript:;">用户列表</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其它系统</a>
                    <dl class="layui-nav-child">
                        <dd><a href="">邮件管理</a></dd>
                        <dd><a href="">消息管理</a></dd>
                        <dd><a href="">授权管理</a></dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
<#--                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">-->
                        <img src="uploads/QQ截图20200120094521.png" style="width:30px;height: 30px">
                        ${userChineseName}
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="${request.contextPath}/goUpdateUser">修改资料</a></dd>
                        <dd><a href="${request.contextPath}/file/gotoUpload">上传头像</a></dd>
                        <dd><a href="${request.contextPath}/reLogin">退出</a></dd>
                    </dl>
                </li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">所有货物</a>
                        <dl class="layui-nav-child">
                            <dd><a id="aWarehouse" href="javascript:;">货物列表</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">所有订单</a>
                        <dl class="layui-nav-child">
                            <dd><a id="aOrder" href="javascript:;">订单列表</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a id="aAddWarehouse" href="javascript:;">商品入库</a></li>
                </ul>
            </div>
        </div>

        <div class="layui-body">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">
                <iframe id="iframepage" width="100%" height="600" src="${request.contextPath}/order/list"
                        scrolling="auto"
                        frameborder="0"></iframe>
            </div>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
            GhostYJ SMM 框架
        </div>
    </div>
</body>
</html>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    function changeFrameHeight() {
        var ifm = document.getElementById("iframepage");
        ifm.height = document.documentElement.clientHeight - 56;
    }

    window.onresize = function () {
        changeFrameHeight();

    };

    $(function () {
        changeFrameHeight();
        // $("#iframepage").contents().find("#divRun").hide();
        // $("#iframepage").contents().find("#divDelete").hide();
    });

    $('#aUser').on('click', function () {
        $('#iframepage').attr('src', '${request.contextPath}/listUser');
    });

    $('#aOrder').on('click', function () {
        $('#iframepage').attr('src', '${request.contextPath}/order/list');
    });

    $('#aWarehouse').on('click', function () {
        $('#iframepage').attr('src', '${request.contextPath}/warehouse/list');
    });

    $('#aAddWarehouse').on('click', function () {
        $('#iframepage').attr('src', '${request.contextPath}/warehouse/add');
    });

    layui.use('element', function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            //console.log(elem)
            layer.msg(elem.text());
        });
    });

</script>