package cn.stylefeng.guns.modular.transaction.service;

import cn.stylefeng.guns.modular.transaction.model.EmpInfo;

import java.security.PrivateKey;

/**
 * @author alin
 */
public interface EmpInfoService {
    /**
     * 获取当前用户信息
     *
     * @return empInfo
     */
    EmpInfo findMine();
}
