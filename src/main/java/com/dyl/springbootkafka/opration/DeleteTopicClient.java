package com.dyl.springbootkafka.opration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteTopicsResult;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 删除主题(Topic)客户端
 *
 */
public class DeleteTopicClient {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.3.100:9092");
        AdminClient adminClient = AdminClient.create(props);
        ArrayList<String> topics = new ArrayList<String>();
        topics.add("Dtest");
        DeleteTopicsResult result = adminClient.deleteTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
