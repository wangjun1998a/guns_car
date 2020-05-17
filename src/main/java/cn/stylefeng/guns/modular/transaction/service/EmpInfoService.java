package cn.stylefeng.guns.modular.transaction.service;

import cn.stylefeng.guns.modular.transaction.model.EmpInfo;

import java.util.Map;

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

    /**
     * 插入用户
     *
     * @param data 用户Json
     * @return map
     */
    Map<String, Object> insertUser(String data);
}
