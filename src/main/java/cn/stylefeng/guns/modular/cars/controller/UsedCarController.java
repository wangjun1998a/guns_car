package cn.stylefeng.guns.modular.cars.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.UsedCar;
import cn.stylefeng.guns.modular.cars.service.IUsedCarService;

/**
 * 车辆交易信息控制器
 *
 * @author fengshuonan
 * @Date 2020-03-17 00:03:33
 */
@Controller
@RequestMapping("/usedCar")
public class UsedCarController extends BaseController {

    private String PREFIX = "/cars/usedCar/";

    @Autowired
    private IUsedCarService usedCarService;

    /**
     * 跳转到车辆交易信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "usedCar.html";
    }

    /**
     * 跳转到添加车辆交易信息
     */
    @RequestMapping("/usedCar_add")
    public String usedCarAdd() {
        return PREFIX + "usedCar_add.html";
    }

    /**
     * 跳转到修改车辆交易信息
     */
    @RequestMapping("/usedCar_update/{usedCarId}")
    public String usedCarUpdate(@PathVariable Integer usedCarId, Model model) {
        UsedCar usedCar = usedCarService.selectById(usedCarId);
        model.addAttribute("item",usedCar);
        LogObjectHolder.me().set(usedCar);
        return PREFIX + "usedCar_edit.html";
    }

    /**
     * 获取车辆交易信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return usedCarService.selectList(null);
    }

    /**
     * 新增车辆交易信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UsedCar usedCar) {
        usedCarService.insert(usedCar);
        return SUCCESS_TIP;
    }

    /**
     * 删除车辆交易信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer usedCarId) {
        usedCarService.deleteById(usedCarId);
        return SUCCESS_TIP;
    }

    /**
     * 修改车辆交易信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UsedCar usedCar) {
        usedCarService.updateById(usedCar);
        return SUCCESS_TIP;
    }

    /**
     * 车辆交易信息详情
     */
    @RequestMapping(value = "/detail/{usedCarId}")
    @ResponseBody
    public Object detail(@PathVariable("usedCarId") Integer usedCarId) {
        return usedCarService.selectById(usedCarId);
    }
}
