package cn.fruitbasket.litchi.seata.purchase.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class PurchaseControllerTest {

    @Autowired
    private PurchaseController purchaseController;

    @Test
    void purchase() {
        purchaseController.purchase("1", UUID.randomUUID().toString().replaceAll("-", ""), 99);
    }
}