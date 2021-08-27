package cn.fruitbasket.litchi.txlcn.c.service.impl;

import cn.fruitbasket.litchi.txlcn.c.entity.BusinessData;
import cn.fruitbasket.litchi.txlcn.c.service.IBusinessDataService;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-26
 */
@Slf4j
@Service
public class BusinessDataServiceTccImpl implements IBusinessDataService {

    private final RedisTemplate<String, String> redisTemplate;

    private final Jackson2HashMapper jackson2HashMapper;

    @TccTransaction
    public void addBusinessData(BusinessData businessData, boolean throwException) {
        redisTemplate.opsForHash().putAll(businessData.getIdempotenceKey(), jackson2HashMapper.toHash(businessData));
    }

    /**
     * 确认事务
     *
     * @param businessData -
     */
    public void confirmAddBusinessData(BusinessData businessData, boolean throwException) {
        log.info("confirmAddBusinessData 写入 Redis 成功 : " + businessData);
    }

    /**
     * 回滚事务
     *
     * @param businessData -
     */
    public void cancelAddBusinessData(BusinessData businessData, boolean throwException) {
        redisTemplate.delete(businessData.getIdempotenceKey());
        log.info("回滚 Redis 成功 idempotenceKey = " + businessData.getIdempotenceKey());
    }

    public BusinessDataServiceTccImpl(RedisTemplate<String, String> redisTemplate,
                                      Jackson2HashMapper jackson2HashMapper) {
        this.redisTemplate = redisTemplate;
        this.jackson2HashMapper = jackson2HashMapper;
    }
}
