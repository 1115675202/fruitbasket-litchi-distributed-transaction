package cn.fruitbasket.litchi.txlcn.a.controller;

import cn.fruitbasket.litchi.txlcn.a.entity.BusinessData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class LcnControllerTest {

    @Autowired
    private LcnController lcnController;

    /**
     * 调用链：a -> b
     * 未出异常，a b 成功保存
     */
    @Test
    void addWithoutException() {
        BusinessData businessData = new BusinessData()
                .setInformation("lcn")
                .setIdempotenceKey(UUID.randomUUID().toString().replaceAll("-", ""));
        lcnController.addBusinessData(businessData, false);

        // 验证 a b 成功保存了相同数据
        Map<String, BusinessData> map = lcnController.getBusinessData(businessData.getIdempotenceKey());
        lcnController.deleteNodeBusinessData(businessData.getIdempotenceKey());
        map.values().forEach(System.out::println);
        Assertions.assertEquals(1, map.values().stream().distinct().count());
    }

    /**
     * 调用链：a -> b
     * a b 先保存，a 抛出异常，不仅 a 回滚，远程调用的 b 也会回滚
     */
    @Test
    void addWithException() {
        BusinessData businessData = new BusinessData()
                .setInformation("lcn")
                .setIdempotenceKey(UUID.randomUUID().toString().replaceAll("-", ""));
        try {
            lcnController.addBusinessData(businessData, true);
        } catch (Exception ignored) {
        }

        // 验证 a b 数据都是空
        Map<String, BusinessData> map = lcnController.getBusinessData(businessData.getIdempotenceKey());
        Assertions.assertTrue(map.values().stream().allMatch(Objects::isNull));
    }
}