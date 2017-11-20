function testFun() {
    var data = {account : 'admin', password :'admin'};
    login(data);
}

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
        localStorage.setItem("token", token);
    }).fail(function (res) {
        console.log('fail = ' + JSON.stringify(res))
    })
}

function test2() {
    var token = localStorage.getItem("token");
    alert(token)
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