package cn.fruitbasket.litchi.seata.account.service.impl;

import cn.fruitbasket.litchi.seata.account.entity.AccountTbl;
import cn.fruitbasket.litchi.seata.account.mapper.AccountTblMapper;
import cn.fruitbasket.litchi.seata.account.service.IAccountTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
@Service
public class AccountTblServiceImpl extends ServiceImpl<AccountTblMapper, AccountTbl> implements IAccountTblService {

    @Override
    public void debit(String userId, int money) {
        save(new AccountTbl().setUserId(userId).setMoney(money));
    }
}
