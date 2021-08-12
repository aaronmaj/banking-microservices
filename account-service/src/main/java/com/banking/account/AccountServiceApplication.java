package com.banking.account;

import com.banking.account.event.model.CustomerChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Aaron MAJAMBO aaronmajb@gmail.com
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope //reloads custom Spring properties in the application configuration
@ComponentScan(basePackages = {"com.banking.core.*", "com.banking.account.*"})
@EnableCaching
@EnableBinding(Sink.class)
public class AccountServiceApplication {

    private final Logger logger = LoggerFactory.getLogger(AccountServiceApplication.class);

    @StreamListener(Sink.INPUT)
    public void loggerSink(CustomerSChangeModel custChange) {
        logger.debug("Received an {} event for organization id {}", custChange.getAction(), custChange.getCustomerId());
    }
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
