var account = 'admin';
var psssword = 'admin';

//登录获取token
// axios.post('http://localhost:8080/login',{
//     account:account,
//     password:psssword
// }).then(function(response){
//     var token = response.data.token;
//     console.log("token = " + token);
//     var timestamp = new Date().getTime();
//     localStorage.setItem("token", token);
//     localStorage.setItem("tokenValidTime", timestamp + 3600000)
// }).catch(function(error){
//     console.log(error);
// });



var testBtn = document.getElementById("testBtn");
testBtn.onclick = function () {
    var token = localStorage.getItem('token');
    console.log("token = " + token);
    var instance = axios.create({
        baseURL:'http://localhost:8080/',
        timeout:5000,
        headers:{'Authorization':token}
    });
    instance.get('/files').then(function (response) {
        var data = response.data;
        fileListVM.items = data;
        console.log(data);
    }).catch(function (error) {
        console.log(error);
    })
};



var ulVM = new Vue({
    el: '#test1',
    data: {
        items: [
            {
                type:'code',
                filename:'test',
                size:'100',
                time:'1213151'
            },
            {
                type:'folder',
                filename:'test',
                size:'100',
                time:'1213151'
            },
            {
                type:'code',
                filename:'test',
                size:'100',
                time:'1213151'
            }
        ]
    }
})

var test2VM = new Vue({
    el: '#test2',
    data: {
        classObject: {
            active: true,
            'text-danger': false
        }
    }
})