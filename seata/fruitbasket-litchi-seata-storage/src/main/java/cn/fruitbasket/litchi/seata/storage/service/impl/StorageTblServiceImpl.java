package cn.fruitbasket.litchi.seata.storage.service.impl;

import cn.fruitbasket.litchi.seata.storage.entity.StorageTbl;
import cn.fruitbasket.litchi.seata.storage.mapper.StorageTblMapper;
import cn.fruitbasket.litchi.seata.storage.service.IStorageTblService;
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
public class StorageTblServiceImpl extends ServiceImpl<StorageTblMapper, StorageTbl> implements IStorageTblService {

    @Override
    public void deduct(String commodityCode, int count) {
        save(new StorageTbl().setCommodityCode(commodityCode).setCount(count));
    }
}
