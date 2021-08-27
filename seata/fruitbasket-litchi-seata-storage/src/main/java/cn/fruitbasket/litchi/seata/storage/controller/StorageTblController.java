package cn.fruitbasket.litchi.seata.storage.controller;


import cn.fruitbasket.litchi.seata.storage.service.IStorageTblService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LiuBing
 * @since 2021-08-27
 */
@RestController
@RequestMapping("/storage")
public class StorageTblController {

    private final IStorageTblService storageTblService;

    @PostMapping
    public void deduct(String commodityCode, int count) {
        storageTblService.deduct(commodityCode, count);
    }

    public StorageTblController(IStorageTblService storageTblService) {
        this.storageTblService = storageTblService;
    }
}
