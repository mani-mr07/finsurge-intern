package com.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.DEVELOPER_QUEUE)
    public void developerlistener(CustomMessage message) {
        System.out.println(message.getMessage()+" it is in developer queue");
//        return "Vanakam from developeristen";
    }
    @RabbitListener(queues=MQConfig.MANAGER_QUEUE)
    public void managerlistener(CustomMessage message){
        System.out.println(message.getMessage() + " it is in manager queue");
    }
//    @RabbitListener(queues=MQConfig.DEVELOPER_QUEUE)
//    public String dev(CustomMessage message){
//        System.out.println(message.getMessage() + " it is in developer queue and in dev method");
//        return "Vankam from the dev";
//    }

}
