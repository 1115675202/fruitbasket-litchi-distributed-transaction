package cn.fruitbasket.litchi.txlcn.b;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author LiuBing
 * @date 2021/8/26
 */
@EnableDistributedTransaction
@MapperScan("cn.fruitbasket.litchi.txlcn.b.mapper")
@SpringBootApplication
public class TxLcnApplicationB {

    public static void main(String[] args) {
        SpringApplication.run(TxLcnApplicationB.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
