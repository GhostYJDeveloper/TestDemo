<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">

</head>
<body>
<ul class="layui-nav">
    <li class="layui-nav-item">
        <a href="${request.contextPath}/warehouse/list">仓库列表<span class="layui-badge">9</span></a>
    </li>
    <li class="layui-nav-item">
        <a href="${request.contextPath}/order/list">订单列表<span class="layui-badge-dot"></span></a>
    </li>
    <li class="layui-nav-item">
        <a href="${request.contextPath}/listUser">用户列表<span class="layui-badge-dot"></span></a>
    </li>
    <li class="layui-nav-item" lay-unselect="">
        <a href="javascript:;"><img src="//t.cn/RCzsdCq" class="layui-nav-img">我</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;">修改信息</a></dd>
            <dd><a href="${request.contextPath}/reLogin">注销</a></dd>
        </dl>
    </li>
</ul>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
    });
</script>

</body>
</html>