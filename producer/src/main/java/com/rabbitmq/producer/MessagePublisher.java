package com.rabbitmq.producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish/{type}")
    public String publishMessage(@RequestBody CustomMessage message, @PathVariable String type) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        Object response=null;
        if(type.equals("developer")){
            System.out.println("DEVELOEPER");
        template.convertAndSend(MQConfig.EXCHANGE, MQConfig.DEVELOPER_ROUTING_KEY, message);
        if(response!=null){
            System.out.println("IN producer "+ response);
        }

        }
        else{
            template.convertAndSend(MQConfig.EXCHANGE,
                    MQConfig.MANAGER_ROUTING_KEY, message);
        }

        return "Message Published";
    }
}
