<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>上传附件</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<form action="upload" enctype="multipart/form-data" method="post">
    <input type="file" name="file"><br/>
    <input type="submit" value="上传">
</form>
</body>
</html>