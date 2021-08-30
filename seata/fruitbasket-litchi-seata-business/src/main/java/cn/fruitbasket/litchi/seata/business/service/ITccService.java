package cn.fruitbasket.litchi.seata.business.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author LiuBing
 * @date 2021/7/29
 */
@LocalTCC
public interface ITccService {

    /**
     * Prepare
     */
    @TwoPhaseBusinessAction(name = "tccTest", commitMethod = "tccTestCommit", rollbackMethod = "tccTestRollback")
    String tccTest(@BusinessActionContextParameter(paramName = "param1") String param1,
                   @BusinessActionContextParameter(paramName = "param2") boolean param2);

    /**
     * Commit
     */
    boolean tccTestCommit(BusinessActionContext businessActionContext);

    /**
     * Rollback
     */
    boolean tccTestRollback(BusinessActionContext businessActionContext);
}
