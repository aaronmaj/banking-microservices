package com.banking.account.event.handler;

import com.banking.account.event.CustomChannels;
import com.banking.account.repository.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(CustomChannels.class)
public class CustomerChangeHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomerChangeHandler.class);

    private RedisRepository redisRepository;
}
