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
    body{margin:0; padding:0;}
</style>

    <body>
    <ul class="layui-nav">
        <li class="layui-nav-item">
            <a href="${request.contextPath}/warehouse/list">仓库列表<span class="layui-badge">${warehouseCount}</span></a>
        </li>
        <li class="layui-nav-item">
            <a href="${request.contextPath}/order/list">订单列表<span class="layui-badge">${orderCount}</span></a>
        </li>
        <li class="layui-nav-item">
            <a href="${request.contextPath}/listUser">用户列表<span class="layui-badge-dot"></span></a>
        </li>
        <li class="layui-nav-item" lay-unselect="">
            <a href="javascript:;"><img src="//t.cn/RCzsdCq" class="layui-nav-img">我</a>
            <dl class="layui-nav-child">
                <dd><a href="${request.contextPath}/goUpdateUser">修改信息</a></dd>
                <dd><a href="${request.contextPath}/reLogin">注销</a></dd>
            </dl>
        </li>
    </ul>
    <div>
        <iframe id="iframepage" width="100%" height="600" src="${request.contextPath}/order/list" scrolling="auto"
                frameborder="0"></iframe>
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
        $("#iframepage").contents().find("#divRun").hide();
        $("#iframepage").contents().find("#divDelete").hide();
    });

    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
    });

</script>