package cn.stylefeng.guns.modular.transaction.service;

import cn.stylefeng.guns.modular.transaction.model.FriendInfo;

import java.util.List;

/**
 * @author alin
 */
public interface FriendInfoService {
    /**
     * 查找当前的好友
     *
     * @return 好友列表
     */
    List<FriendInfo> findFriendInfos();
}
