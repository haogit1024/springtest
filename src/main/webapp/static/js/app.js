var account = 'admin';
var psssword = 'admin';
var domain = "http://localhost:8080/";
//定义一个数组的删除方法
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i].id == val.id) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    console.log("index = " + index);
    if (index > -1) {
        this.splice(index, 1);
    }
};
//当前列表数据
var currentList = new Array();
//导航栏数据
var navigation = new Array();
var navigationObj = new Array();
//导航指针
var navIndex = 0;
//简单的axios实例
var simpleAxios;

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
    simpleAxios = getSimpleAxiosInstance(token);
    init();
}).catch(function(error){
    console.error('登录出错');
    console.debug(error)
});



var testBtn = document.getElementById("testBtn");
testBtn.onclick = function () {
    axios({
        method: 'delete',
        url: domain + "test/delete",
        data: {
            "id":"a"
        }
    })
        .then(function(response) {
            console.log(response);
        })
        .catch(function(response) {
            console.error(response);
        });
};

///////////VM区/////////////
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
                updateList(id, filename);
            } else {
                //文件
                console.log("点击了文件");
                downloadFile(id);
            }
        },
        fileDelete: function (id) {
            deleteFile(id);
        },
        downloadFile: function (id) {
            console.log("下载文件");
            downloadFile(id);
        },
        computedSize: function (byte) {
            if (byte != 0) {
                let sizeUnit = ['B', 'KB', 'MB', 'GB'];
                for (let i = 0; i < sizeUnit.length; i++) {
                    if (byte >= 1024) {
                        byte = byte / 1024;
                    } else {
                        return byte.toFixed(2) + sizeUnit[i];
                    }
                }
                return byte.toFixed(2) + sizeUnit[sizeUnit.length - 1];
            }
            return '-';
            // return byte;
        },
        computedTime: function (timestamp) {
            if (timestamp != 0) {
                return getTimeByTimestamp(timestamp);
            }
            return '-';
        },
        computedClassByType: function (type) {
            if (type != 'folder') {
                return 'fa-file-' + type + '-o';
            } else {
                return 'fa-' + type + '-o';
            }
        }
    }
});

var navVm = new Vue({
    el: "#navBar",
    data: {
        items: []
    },
    methods: {
        navClick: function (fileName, id) {
            var length = navigationObj.length;
            let parentId = localStorage.getItem("parentId");
            for (var i = length - 1; i >= 0; i--) {
                var index = navigationObj[i];
                if (parentId != id) {
                    if (index.fileName === fileName) {
                        listVM.items = index.value;
                        currentList = index.value;
                    } else {
                        navigationObj.pop();
                    }
                } else {
                    break;
                }

            }
        }
    }
});
////////////////////////

//html对象
var newFolderBtn = document.getElementById("newFolder");
var uploadFileInput = document.getElementById("uploadFile");
var uploadBtn = document.getElementById("upload");
var folderNameTr = document.getElementById("folderNameTr");
var folderNameInput = document.getElementById("folderNameInput");
var confirmFolderBtn = document.getElementById("confirmFolderBtn");
var closeFolderBtn = document.getElementById("closeFolderBtn");


//上传文件模块
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
    axios.post(domain + "files", formData, config).then(function (value) {
        //刷新列表date数据
        // currentList.push(value.data);
        currentList.unshift(value.data);
    }).catch(function (reason) {
        alert("上传文件出错");
        console.error(reason.data);
    });
};

//新建文件夹
newFolderBtn.onclick = function () {
    folderNameTr.removeAttribute("style");
};

confirmFolderBtn.onclick = function () {
    let inputVal = folderNameInput.value;
    createFolder(inputVal);
    folderNameTr.style.display = "none";
};

closeFolderBtn.onclick = function () {
    folderNameTr.style.display = "none";
};
