package com.study.orderservice.service;

import com.study.basedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private final NewTopic orderTopic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;


    public OrderProducer(final NewTopic orderTopic, final KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.orderTopic = orderTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(final OrderEvent orderEvent) {
        LOGGER.info("Producing order event: {}", orderEvent);
//        Message<OrderEvent> message =
//                MessageBuilder
//                        .withPayload(orderEvent)
//                        .setHeader(KafkaHeaders.TOPIC, orderTopic.name())
//                        .build();
//        kafkaTemplate.send(message);
        kafkaTemplate.send(orderTopic.name(), orderEvent);
    }
}
