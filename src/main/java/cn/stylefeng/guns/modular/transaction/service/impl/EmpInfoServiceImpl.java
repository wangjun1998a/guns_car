package cn.stylefeng.guns.modular.transaction.service.impl;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.transaction.mapper.EmpInfoMapper;
import cn.stylefeng.guns.modular.transaction.model.EmpInfo;
import cn.stylefeng.guns.modular.transaction.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author alin
 */
@Service
public class EmpInfoServiceImpl implements EmpInfoService {

    @Autowired
    private EmpInfoMapper empInfoMapper;

    @Override
    public EmpInfo findMine() {
//        获取当前登录的用户
        ShiroUser user = ShiroKit.getUser();
        EmpInfo empInfo = empInfoMapper.findMine(user.getId());
        empInfo.setStatus("online");
        return empInfo;
    }
}
