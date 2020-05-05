package cn.stylefeng.guns.modular.transaction.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author alin
 */
@Getter
@Setter
public class EmpInfo {
    //消息的来源id
    private Integer id;
    //消息接收方的id
    private Integer toId;
    private String username;

    private String status;
    //签名
    private String sign;
    //头像
    private String avatar;
    //登陆状态
    private Integer isLogin;

    //消息内容
    private String content;

    //消息的来源类型
    private String type;

    //是否是本人
    private Boolean mine = false;
    //发送消息的时间
    private Date timestamp = new Date();

    //历史消息时间
    private Date historyTime = new Date();
}
