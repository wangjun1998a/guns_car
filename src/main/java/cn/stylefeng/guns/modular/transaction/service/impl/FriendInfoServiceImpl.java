package cn.stylefeng.guns.modular.transaction.service.impl;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.transaction.mapper.FriendInfoMapper;
import cn.stylefeng.guns.modular.transaction.model.EmpInfo;
import cn.stylefeng.guns.modular.transaction.model.FriendInfo;
import cn.stylefeng.guns.modular.transaction.service.FriendInfoService;
import net.sf.jsqlparser.statement.select.First;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alin
 */
@Service
public class FriendInfoServiceImpl implements FriendInfoService {

    @Autowired
    private FriendInfoMapper friendInfoMapper;

    @Override
    public List<FriendInfo> findFriendInfos() {
        ShiroUser user = ShiroKit.getUser();
        List<FriendInfo> list = new ArrayList<>();
        FriendInfo friendInfo = new FriendInfo();

        friendInfo.setGroupname("好友");
        friendInfo.setId(0);
        List<EmpInfo> empInfo = friendInfoMapper.findFriendInfos(user.getId());
        friendInfo.setList(empInfo);
        list.add(friendInfo);
        return list;
    }
}
