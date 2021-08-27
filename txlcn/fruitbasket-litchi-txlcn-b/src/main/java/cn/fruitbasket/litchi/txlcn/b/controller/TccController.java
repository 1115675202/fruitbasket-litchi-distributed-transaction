package cn.fruitbasket.litchi.txlcn.b.controller;

import cn.fruitbasket.litchi.txlcn.b.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.b.service.IBusinessDataService;
import cn.fruitbasket.litchi.txlcn.b.service.impl.BusinessDataServiceTccImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@RequestMapping("/tcc")
public class TccController {

    /**
     * tcc 分布式事务实现
     */
    private final IBusinessDataService businessDataService;

    @PostMapping("{throwException}")
    public void addBusinessData(@RequestBody BusinessData businessData, @PathVariable boolean throwException) {
        businessDataService.addBusinessData(businessData, throwException);
    }

    /**
     * 获取数据
     *
     * @param idempotenceKey -
     * @return -
     */
    @GetMapping("/{idempotenceKey}")
    public BusinessData getBusinessData(@PathVariable String idempotenceKey) {
        return businessDataService.getOne(
                new QueryWrapper<BusinessData>().eq(BusinessData.IDEMPOTENCE_KEY, idempotenceKey));
    }

    /**
     * 删除数据
     *
     * @param idempotenceKey -
     */
    @DeleteMapping("/{idempotenceKey}")
    public void deleteBusinessData(@PathVariable String idempotenceKey) {
        businessDataService.remove(
                new QueryWrapper<BusinessData>().eq(BusinessData.IDEMPOTENCE_KEY, idempotenceKey));
    }

    public TccController(BusinessDataServiceTccImpl businessDataService) {
        this.businessDataService = businessDataService;
    }
}
