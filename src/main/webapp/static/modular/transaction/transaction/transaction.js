/**
 * 车辆交易管理初始化
 */
var Transaction = {
    id: "TransactionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

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
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
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
        ajax.set("transactionId",this.seItem.id);
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
