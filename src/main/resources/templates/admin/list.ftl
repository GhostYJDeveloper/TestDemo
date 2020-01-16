<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">中文名</label>
                    <div class="layui-input-block">
                        <input type="text" name="chineseName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block">
                        <input type="text" name="userName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="lay-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn" id="btnWarehouserList">仓库列表</button>
                </div>
                <div class="layui-inline" style="float:right">
                    <button class="layui-btn" id="btnReLogin">注销登录</button>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-card-body">
        <div style="padding-bottom: 10px;">
            <button class="layui-btn lay-btn-action" data-type="batchDelete">删除</button>
        </div>
        <table id="lay-table" lay-filter="lay-table"></table>
        </table>
        <script type="text/html" id="table-action">
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">详情</a>
            <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-warm" lay-event="update">编辑</a>
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
            url: layui.setter.contextPath + '/rest/user',
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
        //监听工具条
        table.on('tool(lay-table)', function (obj) {
            if (obj.event === 'detail') {
                $.ajax({
                    url: layui.setter.contextPath + '/rest/user/' + obj.data.id,
                    type: 'GET',
                    dataType: 'JSON',
                    success: function (data) {
                        if (data.success) {
                            window.detailUser = data.data;
                            layer.open({
                                type: 2,
                                title: '用户详情',
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
            }
            else if (obj.event === "update") {
                var tr = $(obj.tr);
                $.ajax({
                    url: layui.setter.contextPath + '/rest/user/' + obj.data.id,
                    type: 'GET',
                    dataType: 'JSON',
                    success: function (data) {
                        if (data.success) {
                            window.editUser = data.data;
                            layer.open({
                                type: 2,
                                title: '编辑用户',
                                content: 'edit',
                                area: ['700px', '550px'],
                                btn: ['确定', '取消'],
                                yes: function (index, layero) {
                                    var iframeWindow = window['layui-layer-iframe' + index],
                                        submit = layero.find('iframe').contents().find('#lay-submit');

                                    //监听提交
                                    iframeWindow.layui.form.on('submit(lay-submit)', function (data) {
                                        $.ajax({
                                            url: layui.setter.contextPath + '/updateUser/'+obj.data.id,
                                            type: 'PUT',
                                            dataType: 'JSON',
                                            data:data.field,
                                            success: function (data) {
                                                if (data.success) {
                                                    table.reload('lay-table');
                                                    layer.close(index);
                                                }
                                                else {
                                                    layer.msg(data.message);
                                                }
                                            }
                                        });
                                    });
                                    //触发提交
                                    submit.trigger('click');
                                },
                                success: function (layero, index) {
                                }
                            })
                        }
                        else {
                            layer.msg(data.message);
                        }
                    }
                });
            }
             else if (obj.event === 'delete') {
                layer.confirm('确定删除此数据？', function (index) {
                    $.ajax({
                        url: layui.setter.contextPath + '/rest/user?id=' + obj.data.id,
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
                        url: layui.setter.contextPath + '/rest/user/batch-delete',
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

    $('#btnWarehouserList').on('click', function () {
        location.href = "${request.contextPath}/warehouse/list";
    });
    $('#btnReLogin').on('click', function () {
        location.href = "${request.contextPath}/reLogin";
    });

</script>
</body>
</html>

