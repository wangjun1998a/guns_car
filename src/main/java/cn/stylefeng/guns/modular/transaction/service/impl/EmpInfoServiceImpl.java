package cn.stylefeng.guns.modular.transaction.service.impl;

import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.factory.UserFactory;
import cn.stylefeng.guns.modular.system.model.User;
import cn.stylefeng.guns.modular.system.service.IUserService;
import cn.stylefeng.guns.modular.system.transfer.UserDto;
import cn.stylefeng.guns.modular.transaction.mapper.EmpInfoMapper;
import cn.stylefeng.guns.modular.transaction.model.EmpInfo;
import cn.stylefeng.guns.modular.transaction.service.EmpInfoService;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alin
 */
@Service
public class EmpInfoServiceImpl implements EmpInfoService {

    @Autowired
    private EmpInfoMapper empInfoMapper;
    @Autowired
    private IUserService userService;

    @Override
    public EmpInfo findMine() {
//        获取当前登录的用户
        ShiroUser user = ShiroKit.getUser();
        EmpInfo empInfo = empInfoMapper.findMine(user.getId());
        empInfo.setStatus("online");
        return empInfo;
    }

    @Override
    public Map<String, Object> insertUser(String data) {
        Map<String, Object> map = new HashMap<>();
        UserDto user = JSON.parseObject(data, UserDto.class);

        User theUser = userService.getByAccount(user.getAccount());
        if (theUser != null) {
            throw new ServiceException(BizExceptionEnum.USER_ALREADY_REG);
        }

        // 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreatetime(new Date());
        // 额外
        user.setBirthday(new Date());
        user.setRoleid("6");
        user.setDeptid(28);
        boolean insert = false;
        insert = empInfoMapper.insertUser(user);
//        insert = this.userService.insert(UserFactory.createUser(user));
        user.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588517093279&di=07c8565fa2f3aa998d39f4f4de1112d2&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F25%2F20160225155347_W2eTu.jpeg");
        String id = empInfoMapper.searchPersonId(user);
        user.setId(Integer.parseInt(id));
        //TODO 插入买家本数据
        insert = empInfoMapper.insertOriginalData(user);
        //TODO 插入买家映射卖家数据
        insert = empInfoMapper.insertOriginalDataToSell(user);
        //TODO 插入卖家映射买家数据
        user.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588517183012&di=0de1d42c5cf8a557c36855299227ca3d&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201405%2F24%2F20140524094316_kimjL.jpeg");
        insert = empInfoMapper.insertOriginalDataToBuyer(user);
        map.put("code", insert);
        return map;
    }
}
