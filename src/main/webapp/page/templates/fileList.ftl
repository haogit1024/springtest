<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>files</title>
    <link href="../../static/css/bootstrap.css" rel="stylesheet">
    <link href="../../static/css/font-awesome.min.css" rel="stylesheet">
    <!--<link href="static/css/bootstrap-theme.css" rel="stylesheet">-->
    <script type="text/javascript" src="../../static/js/jquery.js"></script>
    <script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../static/js/app.js"></script>

    <style>
        a:hover {text-decoration: none;}
    </style>
</head>
<body>

<form action="/springtest/file/upload" id="fileForm" style="display: none;">
    <input type="file" name="file" id="file" onchange="fileChange()">
</form>
<button class="btn btn-primary" onclick="fileClick()"><i class="fa fa-upload"></i> 上传文件</button>
<button class="btn btn-info" onchange=""><i class="fa fa-folder-o"></i> 新建文件夹</button>
<button class="btn btn-primary" id="testBtn" onclick="testFun()">测试</button>
<button class="btn btn-primary" id="testBtn2" onclick="test2()">测试2</button>
<div id="fileList"></div>

<div class="panel panel-info">
    <div class="panel-heading">文件列表</div>
    <div class="panel-body">
        <table class="table">
            <thead class="navbar-default">
            <tr>
                <td style="width: 10%"></></td>
                <td style="width: 20%">文件名</td>
                <td style="width: 20%">大小</td>
                <td style="width: 20%">修改时间</td>
            </tr>
            </thead>
            <tbody id="tFileList" class="">
            <#list files as file>
            <tr>
                <td>
                    <#if file.type == "folder">
                        <i class="fa fa-${file.type}"></i>
                    <#else>
                        <i class="fa fa-file-${file.type}-o"></i>
                    </#if>
                </td>
                <td><a href="javascript:void(0)" >${file.filename}</a></td>
                <td>${file.size}</td>
                <td>${file.time}</td>
            </tr>
            </#list>

            </tbody>
        </table>
    </div>
</div>
<script>
    function testClick() {
        alert("test click")
    }

    function fileClick() {
        $('#file').click();
    }

    //上次文件input元素onchange事件触发
    function fileChange() {
        var file = $('#file').val();
        if (file != "") {
            var formData = new FormData($('#fileForm')[0]);
            formData.append("name", "czh");
            $.ajax({
                url:'../file/upload',
                type:'post',
                data:formData,
                processData: false,  // 不处理数据
                contentType: false   // 不设置内容类型
            }).done(function (res) {
                alert('success = ' + res.status)
                //TODO 找一个好一点的保存成功方法
                if (res.status == 0) {
                    location.reload();
                } else {
                    alert("保存出错" + res.statusCode);
                }
            }).fail(function (res) {
                alert('fail = ' + res.status)
            })
        } else {
            alert('没有选择文件')
        }
    }

    //TODO 接新建文件夹的功能
    function newFolder() {

    }
</script>
</body>
</html>
