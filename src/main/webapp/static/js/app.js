var account = 'admin';
var psssword = 'admin';
var domain = "http://localhost:8080/";

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
    localStorage.setItem("parentId", 0);
    init();
}).catch(function(error){
    console.error('登录出错');
    console.debug(error)
});



var testBtn = document.getElementById("testBtn");
testBtn.onclick = function () {

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
                console.log("点击了文件夹");
                localStorage.setItem("parentId", id);
                axiosInstance.get('/files?parsonId=' + id).then(function(response) {
                    var data = response.data;
                    console.log('folder data = ');
                    console.log(data);
                    listVM.items = data;
                    sessionStorage.setItem("parentId", id);
                }).catch(function(error) {
                    console.error('parsonId获取文件列表出错');
                });
            } else {
                //文件
                console.log("点击了文件");
                downloadFile(id);
            }
        }
    }
});

//上传文件模块
var uploadBtn = document.getElementById("upload");
var uploadFileInput = document.getElementById("uploadFile");
uploadBtn.onclick = function () {
    uploadFileInput.click();
};

uploadFileInput.onchange = function (e) {
    var parentId = sessionStorage.getItem("parentId");
    console.log("parentId = " + parentId);
    var file = uploadFileInput.files[0];
    var parentId = localStorage.getItem("parentId");
    console.log("upload parentId = " + parentId);
    var formData = new FormData();
    // formData.append("parsonId", parentId);
    formData.append("file", file);
    formData.append('fileModel', new Blob([JSON.stringify({
        "parentId": parentId,
        "md5": "this is md5",
        "name":"this is name"
    })], {
        type: "application/json"
    }));
    var token = localStorage.getItem('token');
    var config = {
        headers:{'Content-Type':'multipart/form-data','Authorization':token}
    };
    axios.post("http://localhost:8080/files", formData, config).then(function (value) {
        alert(JSON.stringify(value.data))
    }).catch(function (reason) {

    })
};
