package cn.fruitbasket.litchi.txlcn.c.service;

import cn.fruitbasket.litchi.txlcn.c.entity.BusinessData;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-26
 */
public interface IBusinessDataService {

    /**
     * 保存业务数据，测试分布式事务
     *
     * @param businessData   业务数据
     * @param throwException 是否抛出异常，让分布式事务回滚
     */
    void addBusinessData(BusinessData businessData, boolean throwException);
}
