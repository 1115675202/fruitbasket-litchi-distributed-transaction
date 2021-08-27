package cn.fruitbasket.litchi.txlcn.b.service.impl;

import cn.fruitbasket.litchi.txlcn.b.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.b.mapper.BusinessDataMapper;
import cn.fruitbasket.litchi.txlcn.b.service.IBusinessDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-26
 */
@Slf4j
@Service
public class BusinessDataServiceTccImpl extends ServiceImpl<BusinessDataMapper, BusinessData> implements IBusinessDataService {

    private final RestTemplate restTemplate;

    @TccTransaction
    @Transactional(rollbackFor = Exception.class)
    public void addBusinessData(BusinessData businessData, boolean throwException) {
        save(businessData);
        String url = "http://txlcn-c/tcc/" + throwException;
        restTemplate.postForEntity(url, businessData, String.class);
    }

    /**
     * 确认事务
     *
     * @param businessData -
     */
    public void confirmAddBusinessData(BusinessData businessData, boolean throwException) {
        log.info("confirmAddBusinessData 写入 MySQL 成功 : " + businessData);
    }

    /**
     * 回滚事务
     *
     * @param businessData -
     */
    public void cancelAddBusinessData(BusinessData businessData, boolean throwException) {
        removeById(businessData.getId());
        log.error("回滚 MySQL 成功 idempotenceKey = " + businessData.getIdempotenceKey());
    }

    public BusinessDataServiceTccImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
