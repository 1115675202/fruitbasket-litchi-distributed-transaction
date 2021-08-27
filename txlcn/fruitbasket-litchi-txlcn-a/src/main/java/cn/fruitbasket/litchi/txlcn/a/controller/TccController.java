package cn.fruitbasket.litchi.txlcn.a.controller;

import cn.fruitbasket.litchi.txlcn.a.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.a.service.IBusinessDataService;
import cn.fruitbasket.litchi.txlcn.a.service.impl.BusinessDataServiceLcnImpl;
import cn.fruitbasket.litchi.txlcn.a.service.impl.BusinessDataServiceTccImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

    private final RestTemplate restTemplate;

    @PostMapping("{throwException}")
    public void addBusinessDataTcc(@RequestBody BusinessData businessData, @PathVariable boolean throwException) {
        businessDataService.addBusinessData(businessData, throwException);
    }/**
     * 从 a b c 获取数据 tcc
     *
     * @param idempotenceKey -
     * @return -
     */
    @GetMapping("{idempotenceKey}")
    public Map<String, BusinessData> getBusinessData(@PathVariable String idempotenceKey) {
        Map<String, BusinessData> ret = new HashMap<>();
        ret.put("txlcn-a", businessDataService.getOne(
                new QueryWrapper<BusinessData>().eq(BusinessData.IDEMPOTENCE_KEY, idempotenceKey)));

        String nodeB = "txlcn-b";
        String nodeC = "txlcn-c";
        ret.put(nodeB, restTemplate.getForEntity(String.format("http://%s//tcc/" + idempotenceKey, nodeB), BusinessData.class).getBody());
        ret.put(nodeC, restTemplate.getForEntity(String.format("http://%s//tcc/" + idempotenceKey, nodeC), BusinessData.class).getBody());
        return ret;
    }

    /**
     * 从 a b c 删除 数据 tcc
     *
     * @param idempotenceKey -
     */
    @DeleteMapping("{idempotenceKey}")
    public void deleteNodeBusinessData(@PathVariable String idempotenceKey) {
        businessDataService.remove(
                new QueryWrapper<BusinessData>().eq(BusinessData.IDEMPOTENCE_KEY, idempotenceKey));
        restTemplate.delete("http://txlcn-b/tcc/" + idempotenceKey);
        restTemplate.delete("http://txlcn-c/tcc/" + idempotenceKey);
    }

    public TccController(BusinessDataServiceTccImpl businessDataService, RestTemplate restTemplate) {
        this.businessDataService = businessDataService;
        this.restTemplate = restTemplate;
    }
}
