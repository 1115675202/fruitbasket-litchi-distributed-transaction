package cn.fruitbasket.litchi.txlcn.c;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
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
@SpringBootApplication
public class TxLcnApplicationC {

    public static void main(String[] args) {
        SpringApplication.run(TxLcnApplicationC.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
