package cn.fruitbasket.litchi.seata.purchase.controller;


import cn.fruitbasket.litchi.seata.purchase.service.IPurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final IPurchaseService purchaseService;

    @PostMapping
    public void purchase(String userId, String commodityCode, int orderCount) {
        purchaseService.purchase(userId, commodityCode, orderCount);
    }

    public PurchaseController(IPurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }
}
