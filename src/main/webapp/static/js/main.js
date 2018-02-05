var account = 'admin';
var psssword = 'admin';
var domain = "http://localhost:8080/api/";
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
axios.post(domain + 'login',{
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
