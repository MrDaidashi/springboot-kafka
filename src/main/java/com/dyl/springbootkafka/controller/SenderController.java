package com.dyl.springbootkafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SenderController {

    private Logger logger = LoggerFactory.getLogger(SenderController.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 同步发送
     * @return
     */
    @RequestMapping("/send")
    public String sendMessage(){
        for (int i=0;i<3;i++){
            kafkaTemplate.send("Dtest","0","foo"+i);
        }
        return "success";
    }

    @RequestMapping("/sendAsync")
    public String sendMessageAsync(){
        for (int i=0;i<5;i++){
            ListenableFuture<SendResult<String,String>> send = kafkaTemplate.send("Dtest","0","foo"+i);
            send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {

                    logger.error("async send message fail [{}]", ex.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    logger.info("async send message success partition [{}]", result.getRecordMetadata().partition());
                    logger.info("async send message success offest[{}]", result.getRecordMetadata().offset());
                }
            });
        }
        return "success";
    }
}
