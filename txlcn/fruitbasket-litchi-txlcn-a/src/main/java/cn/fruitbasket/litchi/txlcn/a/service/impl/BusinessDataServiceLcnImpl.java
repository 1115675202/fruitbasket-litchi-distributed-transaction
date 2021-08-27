package cn.fruitbasket.litchi.txlcn.a.service.impl;

import cn.fruitbasket.litchi.txlcn.a.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.a.mapper.BusinessDataMapper;
import cn.fruitbasket.litchi.txlcn.a.service.IBusinessDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
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
@Primary
@Slf4j
@Service
public class BusinessDataServiceLcnImpl extends ServiceImpl<BusinessDataMapper, BusinessData> implements IBusinessDataService {

    private final RestTemplate restTemplate;

    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void addBusinessData(BusinessData businessData, boolean throwException) {
        save(businessData);
        String url = "http://txlcn-b/lcn/" + throwException;
        restTemplate.postForEntity(url, businessData, String.class);
        if (throwException) {
            RuntimeException e = new RuntimeException("模拟业务处理异常");
            log.error(e.getMessage());
            throw e;
        }
    }

    public BusinessDataServiceLcnImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
