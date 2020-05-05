package cn.stylefeng.guns.modular.transaction.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alin
 */
@Getter
@Setter
public class ChatInfo {
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;
    /**
     * 数据集合：接收mine（我的信息），friend（好友的信息）
     */
    private Map data = new HashMap();
}
