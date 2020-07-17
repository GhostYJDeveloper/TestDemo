<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户充值</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${request.contextPath}/layuiadmin/layui/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>用户充值</legend>
</fieldset>

<form class="layui-form" action="${request.contextPath}/recharge" lay-filter="lay-form" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input name="userId" type="hidden" value="${user.id?c}">
            <#if user.userName??>
                <input name="chineseName" class="layui-input" readonly type="text" value="${user.userName}">
            <#else>
                <input name="chineseName" class="layui-input" readonly type="text" value="">
            </#if>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">中文名</label>
        <div class="layui-input-block">
            <#if user.chineseName??>
                <input name="chineseName" class="layui-input" readonly type="text" value="${user.chineseName}">
            <#else>
                <input name="chineseName" class="layui-input" readonly type="text" value="">
            </#if>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">余额(元)</label>
        <div class="layui-input-block">
            <input class="layui-input" readonly type="text" value="${user.money?c}">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">充值金额</label>
        <div class="layui-input-block">
            <input style="display: inline;width: 80%!important;" type="text" name="money" class="layui-input" lay-verify="required"
                   onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onblur="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}">
           (元)
        </div>
    </div>
    <br/> <br/> <br/> <br/>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" id="btnApply">立即提交</button>
        </div>
    </div>
</form>
<script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${request.contextPath}/layuiadmin/layui/layui.js"></script>

</body>
</html>