var account = 'admin';
var psssword = 'admin';
var domain = "http://localhost:8080/";
//当前列表数据
var currentList = new Array();
//导航栏数据
var navigation = new Array();
var navigationObj = new Array();
//导航指针
var navIndex = 0;

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

// function foo(event) {
//     var formData = new FormData();
//     formData.append('file', event.target.files[0]);
//     var token = localStorage.getItem('token');
//     var instance = axios.create({
//         baseURL:'http://localhost:8080/',
//         timeout:5000,
//         headers:{
//             'Content-Type': 'multipart/form-data',
//             'Authorization':token
//         }
//     });
//     instance.post('files').then(function(response) {
//         console.log(response);
//     }).catch(function(error) {
//         console.error('files post error');
//         console.error(error);
//     });
// }


var listVM = new Vue({
    el: '#filelist',
    data: {
        items: []
    },
    methods: {
        fileClick: function(id, parsonId, type, filename, event) {
            if (event) {
                event.preventDefault();
            }
            if (type === 'folder') {
                //文件夹
                console.log("点击了文件夹");
                updateList(id);
                updateNav(data, filename, id)
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
        // alert(JSON.stringify(value.data))
        //刷新列表date数据
        currentList.push(value.data);
    }).catch(function (reason) {
        alert("上传文件出错");
        console.error(reason.data);
    });
};

//新建文件夹
var newFolderBtn = document.getElementById("newFolder");
newFolderBtn.onclick = function () {
    createFolder('');
};

var navVm = new Vue({
    el: "#navBar",
    data: {
        items: []
    },
    methods: {
        navClick: function (fileName) {
            var length = navigationObj.length;
            for (var i = length - 1; i >= 0; i--) {
                var index = navigationObj[i];
                if (index.fileName === fileName) {
                    listVM.items = index.value;
                    currentList = index.value;
                } else {
                    navigationObj.pop();
                }
            }
        }
    }
});
