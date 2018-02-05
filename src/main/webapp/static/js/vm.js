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
            return getFileSize(byte);
        },
        computedTime: function (timestamp) {
            if (timestamp != 0) {
                return getTimeByTimestamp(timestamp);
            }
            return '-';
        },
        computedClassByType: function (type) {
            if (type == 'folder') {
                return 'fa-' + type + '-o';
            } else if (type == 'file') {
                return 'fa-file-o';
            } else {
                return 'fa-file-' + type + '-o';
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
