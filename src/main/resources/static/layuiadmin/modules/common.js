/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL

 */

layui.define(function (exports) {
    var $ = layui.$
        , layer = layui.layer
        , laytpl = layui.laytpl
        , setter = layui.setter
        , view = layui.view
        , admin = layui.admin

    /*if(layui.setter.debug)
        console.info("debug");*/

    //公共业务的逻辑处理可以写在此处，切换任何页面都会执行
    //……
    //全局判断ajax操作session是否过期
    $(document).ajaxComplete(function (event, xhr, settings) {
        //console.log(xhr.getResponseHeader("SessionStatus"));
        if (xhr.getResponseHeader("SessionStatus") == "timeout") {
            top.location.href = layui.setter.contextPath + '/pages/auth/login.html';
        }
    });

    //退出
    admin.events.logout = function () {
        //执行退出接口
        admin.req({
            //url: layui.setter.base + 'json/user/logout.js'
            url: layui.setter.contextPath + '/auth/logout'
            , type: 'POST'
            , data: {}
            , done: function (res) { //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行

                //清空本地记录的 token，并跳转到登入页
                admin.exit(function () {
                    //location.href = 'user/login.html';
                    location.href = layui.setter.contextPath + '/pages/auth/login.html';
                });
            }
        });
    };

    /*var obj = {
        func: function(){
        }
    };*/

    //对外暴露的接口
    //exports('common', {});
    //exports('common', obj);
    exports('common', {
        getQueryString: function (name) {
            name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results == null ? "" : decodeURIComponent(results[1]);
        },

        bindType: function (sel) {
            $("#" + sel).append("<option value=''>所有</option>");
            $("#" + sel).append("<option value='1'>手机</option>");
            $("#" + sel).append("<option value='2'>宽带</option>");
            layui.form.render();
        }
    });
});