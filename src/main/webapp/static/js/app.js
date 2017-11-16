function testFun() {
    login('admin', 'admin');
}

function login(account, password) {
    $.ajax({
        url:'../file/upload',
        type:'post',
        data:{
            account : account,
            password : password
        },
        processData: false,  // 不处理数据
        contentType: false   // 不设置内容类型
    }).done(function (res, status, xhr) {
        alert(res);
        alert(status);

    }).fail(function (res) {
        alert('fail = ' + res.status)
    })
}