package com.dyl.springbootkafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsumerListener {

    private Logger logger = LoggerFactory.getLogger(ConsumerListener.class);

    @KafkaListener(id = "foo",topics = "Dtest",groupId = "foo")
    public void listen(ConsumerRecord record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()){
            Object message = kafkaMessage.get();
            logger.info("record =" + record);
            logger.info("message ="+ message);
        }
    }
}
