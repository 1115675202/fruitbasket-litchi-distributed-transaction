package cn.fruitbasket.litchi.seata.account.controller;


import cn.fruitbasket.litchi.seata.account.service.IAccountTblService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
@RestController
@RequestMapping("/account")
public class AccountTblController {

    private final IAccountTblService iAccountTblService;

    @PostMapping
    public void debit(String userId, int money) {
        iAccountTblService.debit(userId, money);
    }

    public AccountTblController(IAccountTblService iAccountTblService) {
        this.iAccountTblService = iAccountTblService;
    }
}
