package cn.fruitbasket.litchi.seata.storage.controller;

import cn.fruitbasket.litchi.seata.storage.service.ITccService;
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
    @RequestMapping(value = "/tcc/commit", produces = "application/json")
    public String purchaseCommit() {
        iTccService.tccTest(ThreadLocalRandom.current().nextInt() + "");
        return "全局事务提交";
    }
}
