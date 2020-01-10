/*alert弹出层*/
function jqalert(param) {

	var title = param.title, //标题
		content = param.content, //内容
		yestext = param.yestext, //确定按钮
		notext = param.notext, //取消按钮
		yesfn = param.yesfn, //成功函数方法
		nofn = param.nofn, //取消函数方法
		nolink = param.nolink, //失败跳转
		yeslink = param.yeslink, //成功跳转
		prompt = param.prompt, //信息
		click_bg = param.click_bg, //判断点击背景，可以关闭弹窗
		productshuoming = param.productshuoming, //产品信息
		slideunlock = param.slideunlock, //滑动验证输出模块
		slideunlockswitch = param.slideunlockswitch, //滑动验证输出模块开关
		slideunlockftn = param.slideunlockftn, //滑动验证输出模块函数调用
		title_icon = param.title_icon, //图标
		CustomizeButton = param.CustomizeButton, //自定义单个按钮
		CustomizeButtonlink = param.CustomizeButtonlink, //自定义单个按钮
		Customizecontent = param.Customizecontent, //自定义文本
		myinput = param.myinput, //自定义输入框
		Customstyle = param.Customstyle, //完全自定义样式
		Customsftn = param.Customsftn,
		clear = param.clear;

	if(Customstyle) {
		var htm = Customstyle;
		$('body').append(htm);
		
		var al = $('#jq-alert');
		
		if(Customsftn){
			Customsftn();
		}
		
		$('#jq-alert').off('click').on('click', function() {
			if(event.target==this){
				setTimeout(function() {
					al.remove();

				}, 300);
			}
		});

	} else {
		if(click_bg === undefined) {
			click_bg = true;
		}
		if(yestext === undefined) {
			yestext = '确认';
		}
		if(!nolink) {
			nolink = 'javascript:void(0);';
		}
		if(!yeslink) {
			yeslink = 'javascript:void(0);';
		}
		if(!CustomizeButtonlink) {
			CustomizeButtonlink = 'javascript:void(0);';
		}

		var htm = '';
		htm += '<div class="jq-alert" id="jq-alert"><div class="alert">';
		if(title) htm += '<h2 class="title">' + title + '</h2>';
		//if(title_icon) htm+=title_icon;

		if(title_icon) htm += '<span class="modal_title_icon flexModel" style="background-image:url(' + title_icon + ');"></span>';

        if(clear){

            htm +='<span id="clearOptionModal"></span>';


        }



        if(prompt) { //判断是否拥有信息
			/*htm += '<div class="content"><div class="prompt">';
			htm += '<p class="prompt-content">'+prompt+'</p>';
			htm += '<input type="text" class="prompt-text"></div>';
			htm +='</div>';*/
			htm += '<div class="content"><div class="prompt">';
			htm += '<p class="prompt-content">' + prompt + '</p>';
			htm += '<input type="tel" class="prompt-text" placeholder="请输入充值金额" maxlength="4" pattern="[0-9]*" style=".26rem"></div>';
			htm += '<p class="prompt-printf-wrap">售价: <span class="prompt-printf" styles="">0</span>元&nbsp;&nbsp;<i></i></p>';
			htm += '</div>';

		} else if(productshuoming) { //判断是否拥有产品信息
			htm += '<div class="pd_content">' + productshuoming + '</div>';
		} else if(productshuoming) { //判断是否拥有产品信息
			htm += '<div class="pd_content">' + productshuoming + '</div>';
		} else if(Customizecontent) { //判断是否拥有产品信息
			htm += '<div class="customizecontent">' + Customizecontent + '</div>';
		} else { //有的话执行内容
			htm += '<div class="content">' + content + '</div>';
		}

		if(slideunlockswitch) { //验证是否打开滑动
			htm += slideunlock;
			$('body').append(htm);
			param.slideunlockftn();
		} else {
			if(CustomizeButton) {
				htm += '<div class="zty-fd-btn"><a href="' + CustomizeButtonlink + '" class="zty-confirm" id="yes_btn">' + CustomizeButton + '</a></div>'
				htm += '</div>';
			} else {
				if(!notext) { //判断是否拥有取消信息
					htm += '<div class="fd-btn"><a href="' + yeslink + '" class="confirm" id="yes_btn">' + yestext + '</a></div>';
					htm += '</div>';
				} else { //有的话执行两个按钮
					htm += '<div class="fd-btn">' +
						'<a href="' + nolink + '"  data-role="cancel" class="cancel">' + notext + '</a>' +
						'<a href="' + yeslink + '" class="confirm"  id="yes_btn">' + yestext + '</a>' +
						'</div>';
					htm += '</div>';
				}
			}

			$('body').append(htm);
		}

		var al = $('#jq-alert');

		/*取消按钮的点击事件*/
		$('body').off('click', '[data-role="cancel"]').on('click', '[data-role="cancel"]', function() { //取消按钮
			//al.remove();
			if(nofn) {
				param.nofn();

			}

		});
		$(' body').off('click', '.alert').on('click', '.alert', function(event) {
			event.stopPropagation();

		});

		/*确定按钮,右侧的按钮点击事件*/
		$('body').off('click', '#yes_btn').on('click', '#yes_btn', function() {

			if(yesfn) {
				param.yesfn();

			}

		});

		if(click_bg === true) { //判断点击背景，可以关闭弹窗
			$('#jq-alert').off('click').on('click', function() {
				if(event.target==this){
					setTimeout(function() {
						al.remove();
	
					}, 300);
				}
			});
		}

		//-----填写其他金额--gf添加--start-service


		/*end*/

        //-----填写其他金额--gf添加--start-service
        $('body').on('keyup','.prompt-text',function(){
            /*2018/6/13修改*/
            var c=$(this);
            if(/[^\d]/.test(c.val())){//替换非数字字符
                var temp_amount=c.val().replace(/[^\d]/g,'');
                $(this).val(temp_amount);
            }

            var ptinf = $(this).val()*1 > 8000? 8000 :$(this).val();
            $(this).val(ptinf);

            var giving = parseInt(~~ptinf/100);
            $('.prompt-printf').html(~~ptinf);
            if( giving >=60){
                $('.prompt-printf-wrap i').html('赠送: '+30+'</em> 元');
            }else if(giving >= 1){
                $('.prompt-printf-wrap i').html('赠送: <em>'+(giving*.5)+'</em> 元');

            }else{
                $('.prompt-printf-wrap i').html('');
            }
        });

        /*20180625 修改*/

        $('#clearOptionModal').on('click',function(){
            $('#jq-alert').remove();
        })





    }

}
/*toast 弹出提示*/
function jqtoast(text, sec) {
	var _this = text;
	var this_sec = sec;
	var htm = '';
	htm += '<div class="jq-toast" styles="display: none;">';
	if(_this) {
		htm += '<div class="toast">' + _this + '</div></div>';
		$('body').append(htm);
		$('.jq-toast').fadeIn();

	} else {
		//jqalert({
		//	title: '提示',
		//	content: '提示文字不能为空',
		//	yestext: '确定'
		//})
	}
	
	if(sec){
		setTimeout(function() {
			$('.jq-toast').fadeOut(function() {
				$(this).remove();
			});
			_this = '';
		}, 500000);
	}else{
		setTimeout(function() {
			$('.jq-toast').fadeOut(function() {
				$(this).remove();
			});
			_this = '';
		}, 1000);
	}
}

/*
"<div class=\"jq-alert\" id=\"jq-alert\">\n" +
"    <div class=\"alert\">\n" +
"        <h2 class=\"updateTitle\">关于7月17日至7月20日南京地市系统升级的公告</h2>\n" +
"        <div class=\"updateMainContent\">\n" +
"            <p>尊敬的用户，您好！</p>\n" +
"            <p class=\"paramTextIndent20\">为了给用户提供更优质的服务，我公司将于2018年7月17日至7月20日期间对南京地市系统进行升级。升级期间将影响南京地区的业务办理/退订、业务安装、号卡激活、充值、各类查询及提醒服务，且7月19日17：00至20日17：00各线上渠道（包括江苏电信网上营业厅、天翼生活APP、“江苏电信”微信公众号、10001短信营业厅等）将暂停南京地区的服务。</p>\n" +
"            <p class=\"paramTextIndent20\">由此给您带来的不便，敬请谅解，详情请咨询客服热线10000。</p>\n" +
"            <p>中国电信股份有限公司江苏分公司<br>二〇一八年七月十七日</p>\n" +
"        </div>\n" +
"        <div class=\"fd-btn\">\n" +
"            <a href=\"javascript:void(0);\" class=\"confirm\" id=\"yes_btn\">朕知道了</a>\n" +
"        </div>\n" +
"    </div>\n" +
"</div>"*/
/*$.ajax({
    url:'/wx/user/popup-notice',
    type:'GET',
    success:function(data){
		var data = data.data;
        console.log('data:'+data);

        if(data != '' && data != undefined && data != null){
            jqalert({
                Customstyle:data,
                Customsftn:function(){
                    $('#yes_btn').off('click').on('click', function() {
                        if(event.target==this){
                            setTimeout(function() {
                                $('#jq-alert').remove();

                            }, 300);
                        }
                    });
                }
            })
        }
    },
    error: function (jqXHR, textStatus, errorThrown) {

    }
});*/
/*setTimeout(function () {
    jqalert({
        Customstyle:"<div class=\"jq-alert\" id=\"jq-alert\">\n" +
        "    <div class=\"alert\">\n" +
        "        <h2 class=\"updateTitle\">关于7月17日至7月20日南京地市系统升级的公告</h2>\n" +
        "        <div class=\"updateMainContent\">\n" +
        "            <p>尊敬的用户，您好！</p>\n" +
        "            <p class=\"paramTextIndent20\">为了给用户提供更优质的服务，我公司将于2018年7月17日至7月20日期间对南京地市系统进行升级。升级期间将影响南京地区的业务办理/退订、业务安装、号卡激活、充值、各类查询及提醒服务，且7月19日17：00至20日17：00各线上渠道（包括江苏电信网上营业厅、天翼生活APP、“江苏电信”微信公众号、10001短信营业厅等）将暂停南京地区的服务。</p>\n" +
        "            <p class=\"paramTextIndent20\">由此给您带来的不便，敬请谅解，详情请咨询客服热线10000。</p>\n" +
        "            <p>中国电信股份有限公司江苏分公司<br>二〇一八年七月十七日</p>\n" +
        "        </div>\n" +
        "        <div class=\"fd-btn\">\n" +
        "            <a href=\"javascript:void(0);\" class=\"confirm\" id=\"yes_btn\">朕知道了</a>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>",
        Customsftn:function(){
            $('#yes_btn').off('click').on('click', function() {
                if(event.target==this){
                    setTimeout(function() {
                        $('#jq-alert').remove();

                    }, 300);
                }
            });
        }
    });
})*/

