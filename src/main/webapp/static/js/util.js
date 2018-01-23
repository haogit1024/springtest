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
        listVM.items = data;
        currentList = data;
        var newLength = navigation.push({
            "全部文件": data
        });
    }).catch(function (error) {
        console.log(error);
    })
}

function getAxiosInstance() {
    // 3_0_bond.tpl.php  3_0_footer.tpl.php  3_0_home.tpl.php	3_0_profile.tpl.php
    var token = localStorage.getItem('token');
    //初始化一个axios
    var instance = axios.create({
        baseURL:'http://localhost:8080/',
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