package cn.stylefeng.guns.modular.transaction.service.impl;

import cn.stylefeng.guns.modular.system.model.Transaction;
import cn.stylefeng.guns.modular.system.dao.TransactionMapper;
import cn.stylefeng.guns.modular.transaction.service.ITransactionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
