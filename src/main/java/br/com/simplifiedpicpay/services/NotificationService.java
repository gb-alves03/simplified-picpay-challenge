package br.com.simplifiedpicpay.services;

import br.com.simplifiedpicpay.config.RabbitMQConfig;
import br.com.simplifiedpicpay.domains.user.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNotification(User user, String message) {
        NotificationMessage notificationMessage = new NotificationMessage(user.getEmail(), message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, notificationMessage);
        System.out.println("Notification message sent to queue");
    }
}
