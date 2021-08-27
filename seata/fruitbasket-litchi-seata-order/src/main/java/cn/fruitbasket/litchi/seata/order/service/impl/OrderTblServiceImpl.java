package cn.fruitbasket.litchi.seata.order.service.impl;

import cn.fruitbasket.litchi.seata.order.entity.OrderTbl;
import cn.fruitbasket.litchi.seata.order.mapper.OrderTblMapper;
import cn.fruitbasket.litchi.seata.order.service.IOrderTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrderTblServiceImpl extends ServiceImpl<OrderTblMapper, OrderTbl> implements IOrderTblService {

    private final RestTemplate restTemplate;

    @Override
    public OrderTbl create(String userId, String commodityCode, int orderCount) {
        OrderTbl order = new OrderTbl().setUserId(userId).setCommodityCode(commodityCode).setCount(orderCount);
        save(new OrderTbl().setUserId(userId).setCommodityCode(commodityCode).setCount(orderCount));
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("userId", userId);
            put("money", orderCount);
        }};
        restTemplate.postForEntity("http://seata-account/account", map, null);
        return order;
    }


    public OrderTblServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
