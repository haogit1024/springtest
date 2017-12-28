var account = 'admin';
var psssword = 'admin';

//登录获取token
axios.post('http://localhost:8080/login',{
    account:account,
    password:psssword
}).then(function(response){
    var token = response.data.token;
    console.log("token = " + token);
    var timestamp = new Date().getTime();
    localStorage.setItem("token", token);
    localStorage.setItem("tokenValidTime", timestamp + 3600000)
}).catch(function(error){
    console.log(error);
});



var testBtn = document.getElementById("testBtn");
testBtn.onclick = function () {
    var token = localStorage.getItem('token');
    console.log("token = " + token);
    var instance = axios.create({
        baseURL:'http://localhost:8080/',
        timeout:5000,
        headers:{'Content-Type':'application/x-www-form-urlencoded','Authorization':token}
    });
    instance.get('/files').then(function (response) {
        var data = response.data;
        // fileListVM.items = data;
        console.log(data);
    }).catch(function (error) {
        console.log(error);
    })
    // testAjax();

    // axios.defaults.baseURL = 'https://api.example.com';
    // axios.defaults.headers.common['Authorization'] = token;
    // axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
};



function testAjax() {
    alert('ajax');
    $.ajax({
        url : "http://localhost:8080/files",
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