package cn.fruitbasket.litchi.seata.business.controller;

import cn.fruitbasket.litchi.seata.business.service.ITccService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author LiuBing
 * @date 2021/7/29
 */
@RestController
public class TccController {

    @Autowired
    private ITccService iTccService;

    /**
     * 模拟全局事务提交
     */
    @GlobalTransactional
    @RequestMapping(value = "/tcc/commit", produces = "application/json")
    public String purchaseCommit() {
        iTccService.tccTest(ThreadLocalRandom.current().nextInt() + "", false);
        return "全局事务提交";
    }

    /**
     * 模拟全局事务回滚
     */
    @GlobalTransactional
    @RequestMapping("/tcc/rollback")
    public String purchaseRollback() {
        iTccService.tccTest(ThreadLocalRandom.current().nextInt() + "", true);
        return "全局事务提交";
    }
}
