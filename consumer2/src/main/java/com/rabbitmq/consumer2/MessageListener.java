package com.rabbitmq.consumer2;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.DEVELOPER_QUEUE)
    public String developerlistener(CustomMessage message) {
        System.out.println(message.getMessage()+" it is in developer queue in connsumer 2");
        return "Vankam from the consumer2";
    }
    @RabbitListener(queues=MQConfig.MANAGER_QUEUE)
    public void managerlistener(CustomMessage message){
        System.out.println(message.getMessage() + " it is in manager queue");
    }
//    @RabbitListener(queues=MQConfig.DEVELOPER_QUEUE)
//    public void dev(CustomMessage message){
//        System.out.println(message.getMessage() + " it is in developer queue and in dev method");
//    }

}
