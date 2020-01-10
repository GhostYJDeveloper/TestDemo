var signPackage;
var url = encodeURIComponent(location.href.split('#')[0]);
var req_url = "http://s.go189.cn/wa/signature/js?url=" + url;
// if (url.indexOf("app.sh.189.cn") > -1 || url.indexOf("m.sh.189.cn") > -1) {
//     req_url = "https://m.sh.189.cn/wap/wx/getJsSign?url=" + url;
// } else {
//     req_url = "https://m.sh.189.cn/wap/wx/getJsSign?url=" + url;
// }
var CommonShare = {};
CommonShare.init = function (async, ope, wxData, wxCallbacks) {
    async = async || true;
    ope = ope || 0;
    $.ajax({
        async: async,
        type: "get",
        url: req_url,
        cache: false,
        success: function (data) {
            if (data != null) {
                signPackage = data;
                if (ope == 0) {
                    CommonShare.forbid();
                } else {
                    CommonShare.control(wxData, wxCallbacks);
                }
            }
        }
    });
}

//自定义分享
CommonShare.control = function (wxData, wxCallbacks) {
    //默认分享配置
    if (!wxData || !wxData.link) {
        var wxData = {
            "imgUrl": "https://shcdn.iceinto.com/img/wt_share.jpg",
            "link": "https://m.sh.189.cn/wx/from/share?t=userHome",
            "desc": '江苏电信',
            "title": '江苏电信'
        };
    }
    if (!wxCallbacks) {
        var wxCallbacks = {
            success: function () {
            },
            cancel: function () {
            },
            fail: function () {
            },
            complete: function () {
            },
            trigger: function () {
            }
        };
    }
    wx.config({
        debug: false,
        appId: signPackage.appId,
        timestamp: signPackage.timestamp,
        nonceStr: signPackage.nonceStr,
        signature: signPackage.signature,
        jsApiList: ['checkJsApi', 'onMenuShareAppMessage', 'onMenuShareTimeline', 'hideMenuItems', 'showMenuItems', "addCard", "closeWindow"]
    });
    wx.ready(function () {
        wx.hideMenuItems({
            menuList: ['menuItem:share:qq', 'menuItem:share:weiboApp', 'menuItem:share:facebook', 'menuItem:share:QZone']
        });
        wx.showMenuItems({
            menuList: ['menuItem:share:appMessage', 'menuItem:share:timeline']
        });
        WeixinApi.ready(wx);
        WeixinApi.shareToFriend(wxData, wxCallbacks);
        WeixinApi.shareToTimeline(wxData, wxCallbacks);
        WeixinApi.shareToWeibo(wxData, wxCallbacks);
        WeixinApi.shareToWeibo(wxData, wxCallbacks);
        WeixinApi.shareToQQ(wxData, wxCallbacks);
    });
}

//禁止分享
CommonShare.forbid = function () {
    wx.config({
        debug: false,
        appId: signPackage.appId,
        timestamp: signPackage.timestamp,
        nonceStr: signPackage.nonceStr,
        signature: signPackage.signature,
        jsApiList: ['hideOptionMenu']
    });
    wx.ready(function () {
        wx.hideOptionMenu();
    });
}