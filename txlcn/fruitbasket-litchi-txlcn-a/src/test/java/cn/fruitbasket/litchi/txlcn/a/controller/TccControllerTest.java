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
class TccControllerTest {

    @Autowired
    private TccController tccController;

    /**
     * 调用链：a -> b -> c
     * 未出异常，a b c 调用 confirm 成功保存
     */
    @Test
    void addWithoutException() {
        BusinessData businessData = new BusinessData()
                .setInformation("tcc")
                .setIdempotenceKey(UUID.randomUUID().toString().replaceAll("-", ""));
        tccController.addBusinessDataTcc(businessData, false);

        // 验证 a b c 成功保存了相同数据
        Map<String, BusinessData> map = tccController.getBusinessData(businessData.getIdempotenceKey());
        tccController.deleteNodeBusinessData(businessData.getIdempotenceKey());
        map.values().forEach(System.out::println);
        Assertions.assertEquals(1, map.values().stream().distinct().count());
    }

    /**
     * 调用链：a -> b -> c
     * a b c 先执行 try 保存，a 抛出异常，a b c 调用 cancel 方法逆操作回退数据
     */
    @Test
    void addWithException() {
        BusinessData businessData = new BusinessData()
                .setInformation("tcc")
                .setIdempotenceKey(UUID.randomUUID().toString().replaceAll("-", ""));
        try {
            tccController.addBusinessDataTcc(businessData, true);
        } catch (Exception ignored) {
        }

        Map<String, BusinessData> map = tccController.getBusinessData(businessData.getIdempotenceKey());
        Assertions.assertTrue(map.values().stream().allMatch(Objects::isNull));
    }
}