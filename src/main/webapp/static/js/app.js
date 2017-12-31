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
    localStorage.setItem("tokenValidTime", timestamp + 3600000);
    init();
}).catch(function(error){
    console.error('登录出错');
});



var testBtn = document.getElementById("testBtn");
testBtn.onclick = function () {
    var token = localStorage.getItem('token');
    console.log("token = " + token);
    var instance = axios.create({
        baseURL:'http://localhost:8080/',
        timeout:5000,
        headers:{
            'Content-Type':'application/x-www-form-urlencoded',
            'Authorization':token,
            'Content-Type': 'multipart/form-data'
        }
    });
    // instance.get('/files').then(function (response) {
    //     var data = response.data;
    //     // fileListVM.items = data;
    //     console.log(data);
    // }).catch(function (error) {
    //     console.log(error);
    // })

};

function foo(event) {
    var formData = new FormData();
    formData.append('file', event.target.files[0]);
    var token = localStorage.getItem('token');
    var instance = axios.create({
        baseURL:'http://localhost:8080/',
        timeout:5000,
        headers:{
            'Content-Type': 'multipart/form-data',
            'Authorization':token
        }
    });
    instance.post('files').then(function(response) {
        console.log(response);
    }).catch(function(error) {
        console.error('files post error');
        console.error(error);
    });
}

var folderData;

var listVM = new Vue({
    el: '#filelist',
    data: {
        items: []
    },
    methods: {
        fileClick: function(id, parsonId, type, event) {
            if (event) {
                event.preventDefault();
            }
            var axiosInstance = getAxiosInstance();
            if (type === 'folder') {
                //文件夹
                axiosInstance.get('/files?parsonId=' + id).then(function(response) {
                    var data = response.data;
                    console.log('folder data = ');
                    console.log(data);
                    listVM.items = data;
                }).catch(function(error) {
                    console.error('parsonId获取文件列表出错');
                });
            } else {
                //文件
                console.log('点击了文件')
                downloadFile(id);
            }
        }
    }
})
