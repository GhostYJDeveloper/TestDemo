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

<form class="layui-form" name="updateForm" lay-filter="lay-form" method="post" enctype="multipart/form-data">
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
            <div id="singleUploadForm" name="singleUploadForm">
                <input id="singleFileUploadInput" type="file" name="file" class="file-input" required/>
            </div>
            <div class="upload-response">
                <div id="singleFileUploadError"></div>
                <div id="singleFileUploadSuccess"></div>
            </div>
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
</form>
<script src="${request.contextPath}/js/main.js"></script>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>
<script type="text/javascript">
    var d = new Date();
    var datetime = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
    $("#updateTime").val(datetime);

    $('#btnApply').on('click', function () {
        singleUpload();
        document.updateForm.action = '${request.contextPath}/updateUser';
    });

</script>

</body>
</html>