var wait = 60;
function time(obj) {

    if (wait == 0) {
        obj.removeAttr("disabled");
        obj.html("获取验证码");
        wait = 60;
    } else {
        obj.attr("disabled", "disabled");
        obj.html(wait + "秒后重发");
        wait--;
        setTimeout(function () {
            time(obj);
        }, 1000)
    }
}


/*只能输入纯数字*/
function noText(obj) {
    var $this = obj;
    if (/[^\d]/.test($this.val())) {//替换非数字字符
        var temp_amount = $this.val().replace(/[^\d]/g, '');
        $this.val(temp_amount);
    }

}

var allMobiles = [
//移动
    "139", "138", "137", "136", "135", "134", "147", "150", "151", "152", "157", "158", "159", "178", "182", "183", "184", "187", "188", "198",
    //联通
    "130", "131", "132", "155", "156", "166", "185", "186", "145", "175", "176",
    //电信
    "133", "153", "177", "173", "180", "181", "189", "199"];


function sendCode(url, obj, mobile) {

    if ((mobile == "") || (mobile.length < 11)) {
        jqtoast("主人，号码格式错误哦~");
        return;
    }

    if ($.inArray(mobile.substring(0, 3), allMobiles) == -1) {
        jqtoast("主人，号码格式错误哦~");
        return;
    }

    time(obj);
    $.ajax({
        type: "POST",
        url: url,
        data: {mobile: mobile},
        success: function (data) {
            console.log(data);
            if (data.code != 0) {
                jqtoast(data.message);
                wait = 0;
            }
            else {

            }
        },
        error: function () {
            jqtoast("发送验证码失败")
        }
    });

}


function validate_explain() {

    /*验证码提示*/

    jqalert({
        title: '验证码仅发送至您填写的手机号',
        content: '1、请确认当前填写的手机号是否正确;</br>' +
        '2、请检查短信是否被手机安全软件拦截;</br>' +
        '3、请确认手机是否处于正常通讯状态;</br>',
        CustomizeButton: '确定',
        yesfn: function () {
            $('#jq-alert').remove();
        }
    });
}
function validateModal(url, mobile) {
    var html = '';

    html += '<div class="validate_modal_to_broadband flexModel absolute-center">\n' +
        '    <div class="modal_bg" ></div>\n' +
        '    <div class="validate_modal_to_broadban-wrap" id="validate_modal_to_broadban-wrap">\n' +
        '        <div class="validate_modal_bd">\n' +
        '            <div class="validate_modal_bd_item flexModel">\n' +
        '                <span class="validate_modal_bd_item_icon"></span>\n' +
        '                <p class="validatePhoneNumber">' + mobile + '</p>\n' +
        '            </div>\n' +
        '             <div class="validate_modal_bd_item flexModel">\n' +
        '                <span class="validate_modal_bd_item_icon"></span>\n' +
        '                     <input type="tel" maxlength="4" placeholder="请输入验证码" id="code" name="code" onkeyup="disableClick();">\n' +
        '                <button class="sendValidateCode flexModel" style="" onclick="sendCode(\'' + url + '\',$(this),\'' + mobile + '\')"> 发送验证码</button>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div>\n' +
        '            <a href="javascript:void(0);" class="full-btn green-btn absolute-center disabled " id="hfBind" style="margin-top:1rem;"  >立即绑定</a>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>';
    $('body').append(html);

    $('.modal_bg').click(function () {
        $('.validate_modal_to_broadband').remove();

    });


}








