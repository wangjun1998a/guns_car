/**
 * 车辆交易信息管理初始化
 */
var UsedCar = {
    id: "UsedCarTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UsedCar.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '已使用年限', field: 'usedImg', visible: true, align: 'center', valign: 'middle'},
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
UsedCar.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UsedCar.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加车辆交易信息
 */
UsedCar.openAddUsedCar = function () {
    var index = layer.open({
        type: 2,
        title: '添加车辆交易信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/usedCar/usedCar_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看车辆交易信息详情
 */
UsedCar.openUsedCarDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车辆交易信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/usedCar/usedCar_update/' + UsedCar.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除车辆交易信息
 */
UsedCar.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/usedCar/delete", function (data) {
            Feng.success("删除成功!");
            UsedCar.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("usedCarId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询车辆交易信息列表
 */
UsedCar.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    UsedCar.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = UsedCar.initColumn();
    var table = new BSTable(UsedCar.id, "/usedCar/list", defaultColunms);
    table.setPaginationType("client");
    UsedCar.table = table.init();
});
