package cn.stylefeng.guns.modular.transaction.mapper;

import cn.stylefeng.guns.modular.transaction.model.EmpInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author alin
 */
@Repository
public interface EmpInfoMapper {
    /**
     * 获取当前登录角色
     *
     * @param id id
     * @return EmpInfo
     */
    EmpInfo findMine(@Param("id") Integer id);
}
