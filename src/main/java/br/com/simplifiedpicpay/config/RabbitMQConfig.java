package br.com.simplifiedpicpay.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "notificationQueue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }
}
