package cn.stylefeng.guns.modular.transaction.controller;

import cn.stylefeng.guns.modular.transaction.model.ChatInfo;
import cn.stylefeng.guns.modular.transaction.model.EmpInfo;
import cn.stylefeng.guns.modular.transaction.model.FriendInfo;
import cn.stylefeng.guns.modular.transaction.service.EmpInfoService;
import cn.stylefeng.guns.modular.transaction.service.FriendInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.Transaction;
import cn.stylefeng.guns.modular.transaction.service.ITransactionService;

import java.util.List;
import java.util.Map;

/**
 * 车辆交易控制器
 *
 * @author fengshuonan
 * @Date 2020-03-17 22:01:07
 */
@Controller
@RequestMapping(value = "/transaction")
public class TransactionController extends BaseController {

    private String PREFIX = "/transaction/transaction/";

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private FriendInfoService friendInfoService;

    @Autowired
    private EmpInfoService empInfoService;

    /**
     * 跳转到车辆交易首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "transaction.html";
    }

    /**
     * 跳转到添加车辆交易
     */
    @RequestMapping("/transaction_add")
    public String transactionAdd() {
        return PREFIX + "transaction_add.html";
    }

    /**
     * 新增用户
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    /**
     * 新增用户实现
     *
     * @param data data
     * @return map
     */
    @ResponseBody
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public Map<String, Object> insertUser(String data) {
        return empInfoService.insertUser(data);

    }

    /**
     * 跳转到修改车辆交易
     */
    @RequestMapping("/transaction_update/{transactionId}")
    public String transactionUpdate(@PathVariable Integer transactionId, Model model) {
        Transaction transaction = transactionService.selectById(transactionId);
        model.addAttribute("item", transaction);
        LogObjectHolder.me().set(transaction);
        return PREFIX + "transaction_edit.html";
    }

    /**
     * 获取车辆交易列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return transactionService.selectList(null);
    }

    /**
     * 新增车辆交易
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Transaction transaction) {
        transactionService.insert(transaction);
        return SUCCESS_TIP;
    }

    /**
     * 删除车辆交易
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer transactionId) {
        transactionService.deleteById(transactionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改车辆交易
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Transaction transaction) {
        transactionService.updateById(transaction);
        return SUCCESS_TIP;
    }

    /**
     * 车辆交易详情
     */
    @RequestMapping(value = "/detail/{transactionId}")
    @ResponseBody
    public Object detail(@PathVariable("transactionId") Integer transactionId) {
        return transactionService.selectById(transactionId);
    }

    /**
     * 获取登录信息
     */
    @RequestMapping(value = "/getUserId")
    @ResponseBody
    public Object getUserId() {
        return transactionService.getUserId();
    }

    @RequestMapping("/findChatInfo")
    @ResponseBody
    public ChatInfo findChatInfo() {
        List<FriendInfo> friendInfos = friendInfoService.findFriendInfos();
        EmpInfo mine = empInfoService.findMine();
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setCode(0);
        chatInfo.setMsg("ok");
        Map map = chatInfo.getData();
        map.put("mine", mine);
        map.put("friend", friendInfos);
        return chatInfo;
    }
}
