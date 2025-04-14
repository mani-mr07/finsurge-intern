package com.kafka.KafkaImplementation;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message from Kafka: " + message);
    }
    @KafkaListener(topics="my-topic",groupId="my-group-2")
    public void listen2(String message){
        System.out.println("Message Received at service 2");
    }
}
