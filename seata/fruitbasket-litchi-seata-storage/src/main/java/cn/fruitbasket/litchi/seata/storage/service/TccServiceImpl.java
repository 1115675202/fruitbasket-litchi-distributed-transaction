package cn.fruitbasket.litchi.seata.storage.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author LiuBing
 * @date 2021/7/29
 */
@Slf4j
@Service
public class TccServiceImpl implements ITccService {

    @Override
    public String tccTest(String param1) {
        log.info("prepare param1 = {}", param1);
        return null;
    }

    @Override
    public boolean tccTestCommit(BusinessActionContext businessActionContext) {
        Map<String, Object> params = businessActionContext.getActionContext();
        log.info("commit param1 = {}", params.get("param1"));
        return true;
    }

    @Override
    public boolean tccTestRollback(BusinessActionContext businessActionContext) {
        Map<String, Object> params = businessActionContext.getActionContext();
        log.info("rollback param1 = {}", params.get("param1"));
        return true;
    }
}
