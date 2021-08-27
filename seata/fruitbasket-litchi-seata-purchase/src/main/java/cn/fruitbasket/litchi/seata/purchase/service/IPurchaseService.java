package cn.fruitbasket.litchi.seata.purchase.service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
public interface IPurchaseService {

    void purchase(String userId, String commodityCode, int orderCount);
}
