package cn.fruitbasket.litchi.seata.purchase.service.impl;

import cn.fruitbasket.litchi.seata.purchase.service.IPurchaseService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
@Service
public class PurchaseServiceImpl implements IPurchaseService {

    private final RestTemplate restTemplate;

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        storage(commodityCode, orderCount);
        order(userId, commodityCode, orderCount);
    }

    public void storage(String commodityCode, int orderCount) {
        Map<String, Object> storage = new HashMap<String, Object>() {{
            put("commodityCode", commodityCode);
            put("count", orderCount);
        }};
        restTemplate.postForEntity("http://seata-storage/storage", storage, null);
    }

    public void order(String userId, String commodityCode, int orderCount) {
        Map<String, Object> storage = new HashMap<String, Object>() {{
            put("userId", userId);
            put("commodityCode", commodityCode);
            put("orderCount", orderCount);
        }};
        restTemplate.postForEntity("http://seata-storage/storage", storage, null);
    }

    public PurchaseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
