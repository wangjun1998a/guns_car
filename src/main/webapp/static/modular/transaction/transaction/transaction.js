/**
 * 车辆交易管理初始化
 */
var Transaction = {
    id: "TransactionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

var socket = null;
var im = null;

/**
 * 初始化表格的列
 */
Transaction.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '图片', field: 'usedImg', visible: true, align: 'center', valign: 'middle'},
        {title: '已使用年限', field: 'serviceLife', visible: true, align: 'center', valign: 'middle'},
        {title: '已行驶公里数', field: 'traveled', visible: true, align: 'center', valign: 'middle'},
        {title: '年检证件', field: 'inspection', visible: true, align: 'center', valign: 'middle'},
        {title: '行驶证', field: 'drivingLicense', visible: true, align: 'center', valign: 'middle'},
        {title: '保险', field: 'insurance', visible: true, align: 'center', valign: 'middle'},
        {title: '保养维修记录', field: 'maintenanceRecord', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Transaction.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Transaction.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加车辆交易
 */
Transaction.openAddTransaction = function () {
    var index = layer.open({
        type: 2,
        title: '添加车辆交易',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/transaction/transaction_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看车辆交易详情
 */
Transaction.openTransactionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车辆交易详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/transaction/transaction_update/' + Transaction.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 聊天
 */
Transaction.openChat = function () {
    if (this.check()) {

        var index = layui.use('layim', function (layim) {
            layim.config({
                // brief: true, //是否简约模式（如果true则不显示主面板）
                init: {
                    url: '/transaction/findChatInfo'
                    , type: 'get' //默认get，一般可不填
                }
            })
            //监听发送消息
            layim.on('sendMessage', function (data) {
                //接收消息人员信息
                var To = data.to;
                var mine = data.mine;

                debugger;

                if (To.type === 'friend') {
                    //layim.setChatStatus('<span style="color:#FF5722;">对方正在输入。。。</span>');
                }
                //添加接收消息方的id
                mine.toId = To.id;
                //添加接收方与己方的关系（Friend 或 group）
                mine.type = To.type;
                // 设置显示在接收方消息的显示位置在左
                mine["mine"] = false;
                var msg = JSON.stringify(mine);
                //保存消息
                // $.ajax({
                //     url: '/msg/saveMsg',
                //     data: {"msg": msg},
                //     type: 'post',
                //     dataType: 'json',
                //     success: function (json) {
                //         if (json && json.code == 200) {
                //             //执行重载
                //         } else {
                //             layer.msg('数据有误');
                //         }
                //     },
                //     error: function () {
                //         layer.msg('系统出错,请联系管理员');
                //     }
                //
                // })
                socket.send(msg);
            });
            var currentUserId;
            $.ajax({
                url: "/transaction/getUserId",
                type: "POST",
                dataType: "json",
                async: false,
                success: function (msg) {
                    currentUserId = msg.id + '';
                    // console.log(msg);
                },
                error: function () {
                    layer.msg('系统出错,请联系管理员');
                }
            });

            //连接websocket的ip地址
            var ip = "localhost";//比如localhost
            //动态修改查
            im = {
                init: function () {
                    if ('WebSocket' in window) {
                        // ${currentUserId}
                        var socketUrl = 'ws://' + ip + '/myHandler?myid=' + currentUserId;
                        socket = new WebSocket(socketUrl);
                        im.startListener();
                    } else {
                        alert('当前浏览器不支持WebSocket功能，请更换浏览器访问。');
                    }
                },
                startListener: function () {
                    if (socket) {
                        // 连接发生错误的回调方法
                        socket.onerror = function () {
                            console.log("通讯连接失败!");
                        };
                        // 连接成功建立的回调方法
                        socket.onopen = function (event) {
                            console.log("通讯连接成功");
                        }
                        // 接收到消息的回调方法
                        socket.onmessage = function (event) {
                            console.log("通讯接收到消息");
                            im.handleMessage(event.data);
                        }
                        // 连接关闭的回调方法
                        socket.onclose = function () {
                            console.log("通讯关闭连接！!");
                        }
                    }
                },
                handleMessage: function (msg) {

                    msg = JSON.parse(msg)
                    //如果是群消息，转换一下id，返回群id的的字段必须是id
                    if (msg.type == "group") {
                        var temId = msg.id;
                        msg.id = msg.toId;
                        msg.toId = temId;
                    }
                    //console.log(msg)
                    layim.getMessage(msg);
                }
            };
            im.init();

        });
        this.layerIndex = index;
    }
};

/**
 * 获取数据信息
 */

Transaction.openDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车辆交易详情',
            // area: '800px', //宽高
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/transaction/detail/' + Transaction.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 删除车辆交易
 */
Transaction.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/transaction/delete", function (data) {
            Feng.success("删除成功!");
            Transaction.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("transactionId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询车辆交易列表
 */
Transaction.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Transaction.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = Transaction.initColumn();
    var table = new BSTable(Transaction.id, "/transaction/list", defaultColunms);
    table.setPaginationType("client");
    Transaction.table = table.init();

});

