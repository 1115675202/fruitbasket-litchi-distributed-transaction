package cn.fruitbasket.litchi.seata.storage.service;

import cn.fruitbasket.litchi.seata.storage.entity.StorageTbl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
public interface IStorageTblService extends IService<StorageTbl> {

    /**
     * 扣除存储数量
     */
    void deduct(String commodityCode, int count);
}
