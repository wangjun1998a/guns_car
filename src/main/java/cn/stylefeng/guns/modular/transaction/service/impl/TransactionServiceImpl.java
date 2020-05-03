package cn.stylefeng.guns.modular.transaction.service.impl;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.model.Transaction;
import cn.stylefeng.guns.modular.system.dao.TransactionMapper;
import cn.stylefeng.guns.modular.transaction.service.ITransactionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 车辆交易 服务实现类
 * </p>
 *
 * @author Alin
 * @since 2020-03-17
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {

    @Override
    public Object getUserId() {
        Map<String, Object> map = new HashMap<>();
        ShiroUser user = ShiroKit.getUser();
        map.put("id", user.getId());
        map.put("name", user.getName());
        return map;
    }
}
