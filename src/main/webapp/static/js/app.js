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
    sessionStorage.setItem("parentId", 0);
    init();
}).catch(function(error){
    console.error('登录出错');
    console.debug(error)
});



var testBtn = document.getElementById("testBtn");
testBtn.onclick = function () {
    var data = {
        name: "this is name",
        age: "this is age"
    }
    console.log(data);
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
                    sessionStorage.setItem("parentId", id);
                }).catch(function(error) {
                    console.error('parsonId获取文件列表出错');
                });
            } else {
                //文件
                downloadFile(id);
                //通过a标签下载文件
                // console.log(event.currentTarget.getAttribute('href'));
                // var aElement = event.currentTarget;
                // aElement.setAttribute('href', domain + "download/" + id);
                // aElement.onclick();
                // aElement.setAttribute('href', 'javascript:void(0)');
                // var filePath = domain + "files/download/" + id;
                // axiosInstance.get(filePath).then(function(response){
                //     console.log("下载文件请求成功")
                //     const content = response;
                //     const blob = new Blob([content]);
                //     const fileName = '测试表格123.md';
                //     const elink = document.createElement('a')
                //     elink.download = fileName
                //     elink.style.display = 'none'
                //     elink.href = URL.createObjectURL(blob)
                //     document.body.appendChild(elink)
                //     elink.click()
                //     URL.revokeObjectURL(elink.href) // 释放URL 对象
                //     document.body.removeChild(elink)
                // }).catch(function(error){
                //     console.debug("下载文件出错");
                // })
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
    var formData = new FormData();
    formData.append("file", file);
    var fileModel = {
        parentId: parentId,
        md5: "thisismd5",
        name: "testfilename"
    }
    formData.append("fileModel", JSON.stringify(fileModel));
    var token = localStorage.getItem('token');
    var config = {
        headers:{'Content-Type':'multipart/form-data','Authorization':token}
    };
    axios.post("http://localhost:8080/files", formData, config).then(function (value) {
        alert(JSON.stringify(value.data))
    }).catch(function (reason) {

    })
};
