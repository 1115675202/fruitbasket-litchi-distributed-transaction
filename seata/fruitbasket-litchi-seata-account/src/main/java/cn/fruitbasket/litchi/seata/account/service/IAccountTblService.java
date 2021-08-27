package cn.fruitbasket.litchi.seata.account.service;

import cn.fruitbasket.litchi.seata.account.entity.AccountTbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
public interface IAccountTblService extends IService<AccountTbl> {

    /**
     * 从用户账户中借出
     */
    void debit(String userId, int money);
}
