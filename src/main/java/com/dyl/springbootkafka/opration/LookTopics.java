package com.dyl.springbootkafka.opration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class LookTopics {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        kafkaListTopics();

    }

    public static void kafkaListTopics() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        // 只需要提供一个或多个 broker 的 IP 和端口
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.3.100:9092");
        // 创建 AdminClient 对象
        AdminClient client = KafkaAdminClient.create(props);
        // 获取 topic 列表
        Set topics = client.listTopics().names().get();
        System.out.println(topics);
    }
}
