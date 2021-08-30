package cn.fruitbasket.litchi.seata.business.service;

import cn.fruitbasket.litchi.seata.business.feign.StorageFeignClient;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author LiuBing
 * @date 2021/7/29
 */
@Slf4j
@Service
public class TccServiceImpl implements ITccService {

    @Autowired
    private StorageFeignClient storageFeignClient;

    @Transactional
    @Override
    public String tccTest(String param1, boolean param2) {
        log.info("prepare param1 = {} param2 = {} ", param1, param2);
        storageFeignClient.purchaseCommit();
        if (param2) throw new RuntimeException("抛出异常回滚");
        return null;
    }

    @Transactional
    @Override
    public boolean tccTestCommit(BusinessActionContext businessActionContext) {
        Map<String, Object> params = businessActionContext.getActionContext();
        log.info("commit param1 = {} param2 = {} ", params.get("param1"), params.get("param2"));
        return true;
    }

    @Transactional
    @Override
    public boolean tccTestRollback(BusinessActionContext businessActionContext) {
        Map<String, Object> params = businessActionContext.getActionContext();
        log.info("rollback param1 = {} param2 = {} ", params.get("param1"), params.get("param2"));
        return true;
    }
}
