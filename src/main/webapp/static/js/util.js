//初始化函数
function init() {
    console.log('init begin');
    initList();
}

//初始化列表函数
function initList(instance) {
    console.log('init list begin');
    var instance = getAxiosInstance();
    instance.get('/files').then(function (response) {
        var data = response.data;
        console.log('filelist = ');
        console.log(data);
        data.reverse();
        listVM.items = data;
        currentList = data;
        updateNav(data, "所有文件", 0);
    }).catch(function (error) {
        console.log(error);
    })
}

function getAxiosInstance() {
    // 3_0_bond.tpl.php  3_0_footer.tpl.php  3_0_home.tpl.php	3_0_profile.tpl.php
    var token = localStorage.getItem('token');
    //初始化一个axios
    var instance = axios.create({
        baseURL:domain,
        timeout:5000,
        headers:{'Content-Type':'application/x-www-form-urlencoded','Authorization':token}
    });
    return instance;
}

function getSimpleAxiosInstance(token) {
    var instance = axios.create({
        baseURL:domain,
        timeout:5000,
        headers:{'Content-Type':'application/x-www-form-urlencoded','Authorization':token}
    });
    return instance;
}

function downloadFile(id) {
    // console.log('点击了文件')
    var token = localStorage.getItem("token");
    var filePath = domain + "files/download/" + id + "?token=" + token;
    var elemIF = document.createElement("iframe");
    elemIF.src = filePath;
    elemIF.style.display = "none";
    document.body.appendChild(elemIF);
}

function createFolder(name) {
    var formData = new FormData();
    if (name === '' || name === null) {
        name = "新建文件夹"
    }
    var parentId = localStorage.getItem("parentId");
    formData.append('fileModel', new Blob([JSON.stringify({
        "parentId": parentId,
        "md5": "this is md5",
        "name": name
    })], {
        type: "application/json"
    }));
    var token = localStorage.getItem('token');
    var config = {
        headers:{'Content-Type':'multipart/form-data','Authorization':token}
    };
    axios.post(domain + "files", formData, config).then(function (value) {
        // alert(JSON.stringify(value.data))
        //刷新列表date数据
        console.log("新建文件夹");
        // currentList.push(value.data);
        currentList.unshift(value.data);
    }).catch(function (reason) {

    })
}

function deleteFile(id) {
    simpleAxios.delete(domain + "files/" + id).then(function (value) {
        console.log("成功删除文件");
        // console.log(value.data);
        currentList.remove(value.data);
    }).catch(function (error) {
        console.error("删除文件出错");
        console.error(error);
    });
}

function updateList(id, filename) {
    localStorage.setItem("parentId", id);
    var axiosInstance = getAxiosInstance();
    axiosInstance.get('/files?parsonId=' + id).then(function(response) {
        var data = response.data;
        console.log('folder data = ');
        console.log(data);
        data.reverse();
        listVM.items = data;
        currentList = data;
        updateNav(data, filename, id)
    }).catch(function(error) {
        console.error('parsonId获取文件列表出错: ' + error.data);
    });
}

function updateNav(data, fileName, id) {
    var parentId = localStorage.getItem("parentId");
    navigation[navIndex] = parentId;
    var listObj = {
        "fileName": fileName,
        "id": id,
        "value": data
    };
    navigationObj.push(listObj);
    navIndex++;
    navVm.items = navigationObj;
    // console.log("navigation = " + JSON.stringify(navigation));
    // console.log("navigationObj = "+ JSON.stringify(navigationObj));
}