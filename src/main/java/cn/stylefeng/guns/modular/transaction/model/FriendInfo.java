package cn.stylefeng.guns.modular.transaction.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author alin
 */
@Getter
@Setter
public class FriendInfo {
    //群名
    private String groupname;
    //群id
    private Integer id;
    //list集合接收多个群
    private List<EmpInfo> list;
}