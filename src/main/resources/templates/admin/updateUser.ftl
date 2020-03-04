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

<form class="layui-form" action="${request.contextPath}/updateUser" name="updateForm" lay-filter="lay-form" method="post" enctype="multipart/form-data">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="hidden" value="${user.id?c}" name="id">
            <input name="username" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input"
                   type="text" value="${user.userName}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input name="password" lay-verify="required" autocomplete="off" placeholder="请输入密码" class="layui-input"
                   value="${user.passWord}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">中文名</label>
        <div class="layui-input-block">
            <input name="chineseName" lay-verify="required" autocomplete="off" placeholder="请输入中文名" class="layui-input"
                   type="text" value="${user.chineseName}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">上传头像</label>
        <div class="layui-input-block">
            <button type="button" id="uploadFile" class="layui-btn layui-btn-primary layui-btn-radius">上传</button>
            <span id="singleFileUploadError"></span>
            <span id="singleFileUploadSuccess"></span>
            <input type="hidden" name="urFilename" id="urFilename">
            <input type="hidden" name="urFiledownloaduri" id="urFiledownloaduri">
            <input type="hidden" name="urFiletype" id="urFiletype">
            <input type="hidden" name="urSize" id="urSize">
            <input type="hidden" name="urSavePath" id="urSavePath">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">修改时间</label>
        <div class="layui-input-block">
            <input type="text" name="updateTime" id="updateTime" readonly class="layui-input">
        </div>
    </div>
    <br/> <br/> <br/> <br/>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1" id="btnApply">立即提交</button>
        </div>
    </div>
    <div class="layui-row" id="popUploadFile" style="display: none;margin-top: 20px">
        <label class="layui-form-label">上传头像</label>
        <div class="layui-input-block">
            <div id="singleUploadForm" name="singleUploadForm">
                <input id="singleFileUploadInput" type="file" name="file" class="file-input" required/>
            </div>
        </div>
    </div>
</form>
<script src="${request.contextPath}/js/main.js"></script>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    var d = new Date();
    var datetime = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    $("#updateTime").val(datetime);

    //上传附件弹框
    layui.use(["jquery",'form', 'layer'], function () {
        var $ = layui.$,layer = layui.layer,form = layui.form;
        $("#uploadFile").click(function(){
            uploadFile();
        });
        function uploadFile(){
            layer.open({
                //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                type:1,
                title:"上传附件",
                area: ['30%','30%'],
                content:$("#popUploadFile"),
                btn: '确定',

                yes:function(index,layero){
                    singleUpload();
                    layer.close(index);
                }
            });
        }
    });
</script>

</body>
</html>