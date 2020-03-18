/**
 * 初始化车辆交易详情对话框
 */
var TransactionInfoDlg = {
    transactionInfoData: {}
};

/**
 * 清除数据
 */
TransactionInfoDlg.clearData = function () {
    this.transactionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TransactionInfoDlg.set = function (key, val) {
    this.transactionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TransactionInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TransactionInfoDlg.close = function () {
    parent.layer.close(window.parent.Transaction.layerIndex);
}

/**
 * 收集数据
 */
TransactionInfoDlg.collectData = function () {
    this
        .set('id')
        .set('name')
        .set('usedImg')
        .set('serviceLife')
        .set('traveled')
        .set('inspection')
        .set('drivingLicense')
        .set('insurance')
        .set('maintenanceRecord');
}

/**
 * 提交添加
 */
TransactionInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/transaction/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Transaction.table.refresh();
        TransactionInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.transactionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TransactionInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/transaction/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Transaction.table.refresh();
        TransactionInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.transactionInfoData);
    ajax.start();
}

$(function () {
    var avatarUp = new $WebUpload("usedImg");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
});
