package chian.cloud.productservice;

import chian.cloud.productservice.mq.UserMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@EnableDiscoveryClient
//@EnableFeignClients
@EnableHystrixDashboard
@SpringBootApplication
//@EnableBinding(Sink.class)
public class ProductServiceApplication {

    private Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

//    @StreamListener(Sink.INPUT)
//    public void onUserMsgSink(UserMsg userMsg){
//        this.logger.debug("receive user msg:{}",userMsg);
//    }
}