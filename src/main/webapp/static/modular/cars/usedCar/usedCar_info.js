/**
 * 初始化车辆交易信息详情对话框
 */
var UsedCarInfoDlg = {
    usedCarInfoData : {}
};

/**
 * 清除数据
 */
UsedCarInfoDlg.clearData = function() {
    this.usedCarInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UsedCarInfoDlg.set = function(key, val) {
    this.usedCarInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UsedCarInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UsedCarInfoDlg.close = function() {
    parent.layer.close(window.parent.UsedCar.layerIndex);
}

/**
 * 收集数据
 */
UsedCarInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('usedImg')
    .set('traveled')
    .set('inspection')
    .set('drivingLicense')
    .set('insurance')
    .set('maintenanceRecord');
}

/**
 * 提交添加
 */
UsedCarInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/usedCar/add", function(data){
        Feng.success("添加成功!");
        window.parent.UsedCar.table.refresh();
        UsedCarInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.usedCarInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UsedCarInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/usedCar/update", function(data){
        Feng.success("修改成功!");
        window.parent.UsedCar.table.refresh();
        UsedCarInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.usedCarInfoData);
    ajax.start();
}

$(function() {

});
