package br.com.simplifiedpicpay.services;

import br.com.simplifiedpicpay.config.RabbitMQConfig;
import br.com.simplifiedpicpay.dtos.NotificationDtoRequest;
import br.com.simplifiedpicpay.infra.exceptions.NotificationServiceOutException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveNotification(NotificationMessage notificationMessage) {
        String email = notificationMessage.getEmail();
        String message = notificationMessage.getMessage();
        NotificationDtoRequest notificationRequest = new NotificationDtoRequest(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Error to send notification");
            throw new NotificationServiceOutException("Notification Service is out");
        }

        System.out.println("Notification was sent to the user");
    }
}
