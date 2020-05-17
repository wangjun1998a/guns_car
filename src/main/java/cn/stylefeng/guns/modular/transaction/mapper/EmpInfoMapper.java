package cn.stylefeng.guns.modular.transaction.mapper;

import cn.stylefeng.guns.modular.system.transfer.UserDto;
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

    /**
     * 查询新插入的数据ID
     *
     * @param user javaBean
     * @return id
     */
    String searchPersonId(UserDto user);

    /**
     * 插入通讯数据
     *
     * @param user javaBean
     * @return bool
     */
    boolean insertOriginalData(UserDto user);

    /**
     * 插入买家对卖家的数据
     *
     * @param user javaBean
     * @return bool
     */
    boolean insertOriginalDataToSell(UserDto user);

    /**
     * 插入卖家对买家的数据
     *
     * @param user javaBean
     * @return bool
     */
    boolean insertOriginalDataToBuyer(UserDto user);

    /**
     * 插入用户
     *
     * @param user javaBean
     * @return bool
     */
    boolean insertUser(UserDto user);
}
