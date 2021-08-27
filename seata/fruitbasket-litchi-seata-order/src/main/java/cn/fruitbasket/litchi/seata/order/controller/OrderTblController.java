package cn.fruitbasket.litchi.seata.order.controller;


import cn.fruitbasket.litchi.seata.order.entity.OrderTbl;
import cn.fruitbasket.litchi.seata.order.service.IOrderTblService;
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
@RequestMapping("/order")
public class OrderTblController {

    private final IOrderTblService orderTblService;

    @PostMapping
    public OrderTbl create(String userId, String commodityCode, int orderCount) {
        return orderTblService.create(userId, commodityCode, orderCount);
    }

    public OrderTblController(IOrderTblService orderTblService) {
        this.orderTblService = orderTblService;
    }
}
