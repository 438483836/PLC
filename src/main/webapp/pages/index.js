var base_url = 'http://localhost:8080/WlDemo';


var login = function () {
    var uids = document.getElementById("user").value;
    var pwds = document.getElementById("pwd").value;
    $.ajax({
        url: base_url + "/login",
        cache: false,
        data: {user: uids, pwd: pwds},
        success: function (data) {
            noticeBox('登录成功,您的角色为：' + data);
        },
        error: function () {
            errorBox('登录失败！');
        }
    });
}

var up = function () {
    var barcode = document.getElementById("barcode").value;
    var smt = document.getElementById("smt").value;
    var exit = document.getElementById("exit").value;
    console.log(barcode+"--"+smt+"--"+exit);
    $.ajax({
        url: base_url + "/upload",
        cache: false,
        data: {barcode: barcode, smt: smt, exit: exit},
        success: function (data) {
            console.log(data);
            if("上件成功"==data){
                noticeBox(data);
            }else{
                warningBox("上件失败:"+data)
            }
        },
        error: function () {
            errorBox("请求失败！");
        }
    });
}

var pack = function ( e,s) {
    //var e = document.getElementById("export").value;
   // var status = document.getElementById("status").value;
    $.ajax({
        url: base_url + "/pack",
        cache: false,
        data: {export: e, status: s},
        success: function (data) {
            console.log(data);
            if("失败"==data){
                warningBox("失败");
            }else{
                noticeBox("下件数量:"+data);
            }
        },
        error: function () {
            errorBox("请求失败！");
        }
    });
}



var noticeBox = function (msg) {
    event.preventDefault();
    event.stopPropagation();
    return $.growl.notice({
        title: "提醒",
        message: msg
    });
}
var errorBox = function (msg) {
    event.preventDefault();
    event.stopPropagation();
    return $.growl.error({
        title: "错误",
        message: msg
    });

}
var warningBox = function (msg) {
    event.preventDefault();
    event.stopPropagation();
    return $.growl.warning({
        title: "警告",
        message: msg
    });
}



