package cn.fruitbasket.litchi.txlcn.c.controller;

import cn.fruitbasket.litchi.txlcn.c.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.c.service.IBusinessDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

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
public class LcnController {

    private final IBusinessDataService businessDataService;

    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;

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
    @GetMapping("{idempotenceKey}")
    public BusinessData getBusinessData(@PathVariable String idempotenceKey) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> map = opsForHash.entries(idempotenceKey);
        BusinessData ret = objectMapper.convertValue(map, BusinessData.class);
        return ret.getIdempotenceKey() == null ? null : ret;
    }

    /**
     * 获取数据
     *
     * @param idempotenceKey -
     * @return -
     */
    @DeleteMapping("{idempotenceKey}")
    public void deleteBusinessData(@PathVariable String idempotenceKey) {
        redisTemplate.delete(idempotenceKey);
    }

    public LcnController(IBusinessDataService businessDataService,
                         RedisTemplate<String, String> redisTemplate,
                         ObjectMapper objectMapper) {
        this.businessDataService = businessDataService;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }
}
