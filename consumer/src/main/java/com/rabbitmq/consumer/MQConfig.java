package com.rabbitmq.consumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;



@Configuration
public class MQConfig {

    public static final String DEVELOPER_QUEUE = "developer_queue";
    public static final String MANAGER_QUEUE="manager_queue";
    public static final String EXCHANGE = "message_exchange";
    public static final String MANAGER_ROUTING_KEY = "manager_routingKey";
    public static final String DEVELOPER_ROUTING_KEY="developer_routingkey";


    @Bean
    public Queue developerqueue() {
        return  new Queue(DEVELOPER_QUEUE);
    }
    @Bean
    public Queue managerqueue() {
        return  new Queue(MANAGER_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding developerbinding(Queue developerqueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(developerqueue)
                .to(exchange)
                .with(DEVELOPER_ROUTING_KEY);
    }
    @Bean
    public Binding managerbinding(Queue managerqueue, TopicExchange exchange) {
        return BindingBuilder
                .bind(managerqueue)
                .to(exchange)
                .with(MANAGER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {

        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }

}
