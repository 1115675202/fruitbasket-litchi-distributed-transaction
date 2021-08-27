package cn.fruitbasket.litchi.seata.order.service;

import cn.fruitbasket.litchi.seata.order.entity.OrderTbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
public interface IOrderTblService extends IService<OrderTbl> {

    /**
     * 创建订单
     */
    OrderTbl create(String userId, String commodityCode, int orderCount);

}
