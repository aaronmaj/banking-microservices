package com.banking.customer.events.source;

import com.banking.customer.events.model.CustomerChangeModel;
import com.banking.customer.filters.UserContext;
import com.banking.customer.events.model.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;

//@Component
public class SimpleSourceBean {
/*
    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);
    private final Source source;

    public SimpleSourceBean(Source source) {
        this.source = source;
    }

    public void publishCustomerChange(Action action, String customerId) {
        logger.debug("Sending Kafka message {} for Customer Id: {}", action, customerId);

        CustomerChangeModel change = new CustomerChangeModel(
                CustomerChangeModel.class.getTypeName(),
                action,
                customerId,
                UserContext.getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }
*/
}
