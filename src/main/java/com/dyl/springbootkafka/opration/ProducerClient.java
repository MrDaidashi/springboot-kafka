package com.dyl.springbootkafka.opration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerClient {

    public static void main(String[] args) {
        Properties props = new Properties();
        //指定kafka的服务器的地址
        props.put("bootstrap.servers","192.168.3.100:9092");
        //消息的确认机制
        props.put("acks","all");
//        props.put("delivery.timeout.ms",300000);
        //批量发送的大小
//        props.put("batch.size",16384);
        //消息的延迟
        props.put("linger.ms",1);
        //消息缓冲区的大小
//        props.put("buffer.memory",33554432);
        //定义key和value的序列化
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //初始化
        Producer<String,String> producer = new KafkaProducer<String, String>(props);

        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("Dtest","mykey","hello world message");

        producer.send(producerRecord);

        producer.close();

    }
}
