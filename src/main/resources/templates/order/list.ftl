<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>订单列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<div class="layui-fluid" id="divRun">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-inline">
                <label class="layui-form-label">订单编号</label>
                <div class="layui-input-block">
                    <input type="text" name="number" placeholder="请输入" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn" lay-submit lay-filter="lay-search">
                    <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                </button>
            </div>
            <div class="layui-inline">
                <button class="layui-btn" id="btnWarehouseList">货物列表</button>
            </div>
            <div class="layui-inline" style="float:right">
                <button class="layui-btn" id="btnIndex">返回首页</button>
            </div>
        </div>
    </div>
</div>
<div class="layui-card-body">
    <div style="padding-bottom: 10px;" id="divDelete">
        <button class="layui-btn lay-btn-action" data-type="batchDelete">删除</button>
    </div>
    <table id="lay-table" lay-filter="lay-table"></table>
    </table>
    <script type="text/html" id="table-action">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">详情</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete"><i
                    class="layui-icon layui-icon-delete"></i>删除</a>
    </script>
</div>
</div>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script>
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
            url: layui.setter.contextPath + '/rest/order',
            cols: [[
                {type: 'checkbox', fixed: 'left'},
                {field: 'id', title: 'Id', align: 'center'},
                {field: 'userChineseName', title: '用户名称', align: 'center'},
                {field: 'cargoNumber', title: '货物编号', align: 'center'},
                {field: 'cargoName', title: '货物名称', align: 'center'},
                {field: 'type', title: '货物类别', align: 'center'},
                {field: 'orderNumber', title: '订单编号', align: 'center'},
                {field: 'orderDate', title: '下单日期', align: 'center'},
                {field: 'buyCount', title: '下单数量', align: 'center'},
                {title: '操作', align: 'center', width: 180, fixed: 'right', toolbar: '#table-action'}
            ]],
            text: {none: '无数据'},
            page: true,
            loading: true
        });
        //监听工具条
        table.on('tool(lay-table)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                var tr = $(obj.tr);
                $.ajax({
                    url: layui.setter.contextPath + '/rest/orderDetail?id=' + obj.data.id + "&userChineseName="
                        + obj.data.userChineseName + "&typeName=" + obj.data.type,
                    type: 'GET',
                    dataType: 'JSON',
                    success: function (data) {
                        if (data.success) {
                            //var iframeWindow = window['layui-layer-iframe' + index];
                            //iframeWindow.layui.init(data.data.id);
                            window.detailUser = data.data;
                            var index = layer.open({
                                type: 2,
                                title: '订单详情',
                                content: 'detail',
                                area: ['700px', '550px'],
                                btn: '确定',
                                success: function (layero, index) {
                                }
                            });
                        } else {
                            layer.msg(data.message);
                        }
                    }
                });
            } else if (obj.event === 'delete') {
                layer.confirm('确定删除此数据？', function (index) {
                    $.ajax({
                        url: layui.setter.contextPath + '/rest/order?id=' + obj.data.id,
                        type: 'DELETE',
                        dataType: 'JSON',
                        success: function (data) {
                            if (data.success) {
                                //obj.del();
                                //layer.close(index);
                                table.reload('lay-table');
                            } else {
                                layer.msg(data.message);
                            }
                        }
                    });
                });
            }
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
        //事件
        var active = {
            batchDelete: function () {
                var checkStatus = table.checkStatus('lay-table'),
                    checkData = checkStatus.data; //得到选中的数据

                if (checkData.length === 0) {
                    return layer.msg('请选择数据');
                }

                //console.log(checkData);
                layer.confirm('确定删除吗？', function (index) {
                    $.ajax({
                        url: layui.setter.contextPath + '/rest/order/batch-delete',
                        type: 'DELETE',
                        dataType: 'JSON',
                        data: JSON.stringify(checkStatus.data),
                        success: function (data) {
                            if (data.success) {
                                //layer.close(index);
                                layer.msg('已删除');
                                table.reload('lay-table');
                            } else {
                                layer.msg(data.message);
                            }
                        }
                    });
                });
            }
        };
        $('.layui-btn.lay-btn-action').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
    $('#btnWarehouseList').on('click', function () {
        location.href = "${request.contextPath}/warehouse/list";
    });

    $('#btnIndex').on('click', function () {
        location.href = "${request.contextPath}/goIndex";
    });
</script>
</body>
</html>

