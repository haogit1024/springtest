function testFun() {
    var data = {account : 'admin', password :'admin'};
    var loginObject = data;
    localStorage.setItem("loginObject", loginObject);
    login(data);
}

//输入ccount, password
function login(data) {
    var _ajax = $.ajax({
        url:'../../login',
        type:'post',
        dataType: 'json',
        data: JSON.stringify(data),
        processData: false,  // 不处理数据
        contentType: 'application/json'   // 不设置内容类型
    }).done(function (res) {
        // alert(_ajax.status);
        var token = res.token;
        var timestamp = new Date().getTime();
        localStorage.setItem("token", token);
        localStorage.setItem("tokenValidTime", timestamp + 3600000)
    }).fail(function (res) {
        console.log('fail = ' + JSON.stringify(res))
    })
}

function test2() {
    var tokenValidTime = localStorage.getItem("tokenValidTime");

    var now = new Date().getTime();
    if (now > tokenValidTime) {
        var loginObject = localStorage.getItem("loginObject");
        login(loginObject)
    }
}
function testAjax() {
    $.ajax({
        url : "../../files",
        type:"GET",
        beforeSend: function (xhr) {
            var token = window.localStorage.getItem("token");
            // alert("token = " + token)
            xhr.setRequestHeader("Authorization", token);
        }
    }).done(function (res) {
        console.log("success: " + JSON.stringify(res))
    }).fail(function (res) {
        console.log("fail: " + JSON.stringify(res))
    });
}