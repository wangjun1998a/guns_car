package cn.stylefeng.guns.modular.transaction.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupInfo {
    //群id(部门id)
    private Integer id;
    //群名
    private String groupname;
    //群头像
    private String avatar;
}