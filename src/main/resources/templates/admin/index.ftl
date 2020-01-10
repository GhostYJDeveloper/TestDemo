<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/style/admin.css" media="all">

</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">开始时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="startTime" id="startTime" class="layui-input dateTime" lay-verify="required">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">结束时间</label>
                    <div class="layui-input-block">
                        <input type="text" name="endTime" id="endTime" class="layui-input dateTime" lay-verify="required">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="lay-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-primary" id="btnList">用户列表</button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">

            <table id="lay-table" lay-filter="lay-table"></table>
            <script type="text/html" id="tplHeadImg">
                <div>
                    <img style="width: 30%;" src="{{ d.headImgUrl }}">
                </div>
            </script>
        </div>
    </div>
</div>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script>

    function search(startTime, endTime){
        layui.use(['table'], function () {
            var $ = layui.$,
                table = layui.table;


            table.render({
                elem: '#lay-table',
                url: layui.setter.contextPath + '/get-data',
                data:{"starTime":startTime, "endTime": endTime},
                cols: [[
                    {field: 'all', title: '成功邀请的数量', align: 'center'},
                    {field: 'allByPhone', title: '分享成功的人数', align: 'center'},
                    {field: 'receive', title: '兑换数量', align: 'center'},
                    {field: 'receiveByPhone', title: '兑换人数', align: 'center'}

                ]],
                text: {none: '无数据'},
                page: true,
                loading: true
            });
        });
    }

    layui.config({
        base: '${request.contextPath}/layuiadmin/'
    }).extend({
        index: 'lib/index'
    }).use(['index', 'table', 'form', 'laydate'], function () {
        var $ = layui.$,
            form = layui.form,
            table = layui.table;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#startTime',
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime',
            type: 'datetime'
        });


        //监听搜索
        form.on('submit(lay-search)', function (data) {
            search(data.field.startTime, data.field.endTime);
        });

        table.render({
            elem: '#lay-table',
            url: layui.setter.contextPath + '/get-data',
            cols: [[
                {field: 'all', title: '成功邀请的数量', align: 'center'},
                {field: 'allByPhone', title: '分享成功的人数', align: 'center'},
                {field: 'receive', title: '兑换数量', align: 'center'},
                {field: 'receiveByPhone', title: '兑换人数', align: 'center'}
            ]],
            text: {none: '无数据'},
            page: true,
            loading: true
        });

        //监听搜索
        form.on('submit(lay-search)', function (data) {
            var field = data.field;
            //执行重载
            table.reload('lay-table', {
                where: field,
                page: {curr: 1}
            });
        });


    });
    $('#btnList').on('click', function () {
        location.href = "${request.contextPath}/listUser";
    });

</script>
</body>
</html>

