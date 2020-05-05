package cn.stylefeng.guns.modular.transaction.mapper;

import cn.stylefeng.guns.modular.transaction.model.EmpInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author alin
 */
@Repository
public interface FriendInfoMapper {

    /**
     * Mapper findFriendInfos
     *
     * @param id id
     * @return List<EmpInfo>
     */

    List<EmpInfo> findFriendInfos(@Param("id") Integer id);


}
