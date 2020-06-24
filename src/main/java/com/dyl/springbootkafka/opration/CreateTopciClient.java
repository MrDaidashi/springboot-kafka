package com.dyl.springbootkafka.opration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CreateTopciClient {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.3.100:9092");
        AdminClient adminClient = AdminClient.create(props);
        ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
        NewTopic newTopic = new NewTopic("Dtest",1,(short)1);
        topics.add(newTopic);
        CreateTopicsResult result = adminClient.createTopics(topics);
        try{
            result.all().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
