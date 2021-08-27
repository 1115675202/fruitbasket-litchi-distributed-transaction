package cn.fruitbasket.litchi.txlcn.b.service.impl;

import cn.fruitbasket.litchi.txlcn.b.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.b.mapper.BusinessDataMapper;
import cn.fruitbasket.litchi.txlcn.b.service.IBusinessDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-26
 */
@Primary
@Slf4j
@Service
public class BusinessDataServiceLcnImpl extends ServiceImpl<BusinessDataMapper, BusinessData> implements IBusinessDataService {

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void addBusinessData(BusinessData businessData, boolean throwException) {
        save(businessData);
    }

}
