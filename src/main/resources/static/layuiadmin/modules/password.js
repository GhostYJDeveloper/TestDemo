/**
 * 密码校验
 */

layui.define(['form'], function (exports) {
    var $ = layui.$
        ,form = layui.form;

    //自定义验证
    form.verify({
        //密码
        pass: [
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/
            ,'密码至少8个字符，至少一个大写字母，一个小写字母，一个数字和一个特殊字符'
        ]
        //确认密码
        , repass: function (value) {
            if (value !== $('#password').val()) {
                return '两次密码输入不一致';
            }
        }
    });

    //对外暴露的接口
    exports('password', {});
});