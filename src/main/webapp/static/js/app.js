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
