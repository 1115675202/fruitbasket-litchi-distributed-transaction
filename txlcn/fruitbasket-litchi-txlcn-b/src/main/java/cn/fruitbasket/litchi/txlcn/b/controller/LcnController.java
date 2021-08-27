package cn.fruitbasket.litchi.txlcn.b.controller;

import cn.fruitbasket.litchi.txlcn.b.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.b.service.IBusinessDataService;
import cn.fruitbasket.litchi.txlcn.b.service.impl.BusinessDataServiceLcnImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-26
 */
@Slf4j
@RestController
@RequestMapping("/lcn")
public class LcnController {

    /**
     * lcn 分布式事务实现
     */
    private final IBusinessDataService businessDataService;

    @PostMapping("/{throwException}")
    public void addBusinessData(@RequestBody BusinessData businessData, @PathVariable boolean throwException) {
        businessDataService.addBusinessData(businessData, throwException);
    }

    public LcnController(BusinessDataServiceLcnImpl businessDataService) {
        this.businessDataService = businessDataService;
    }
}
