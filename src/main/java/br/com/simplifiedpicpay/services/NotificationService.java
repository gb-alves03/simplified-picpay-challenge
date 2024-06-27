package br.com.simplifiedpicpay.services;

import br.com.simplifiedpicpay.domains.user.User;
import br.com.simplifiedpicpay.dtos.NotificationDtoRequest;
import br.com.simplifiedpicpay.infra.exceptions.NotificationServiceOutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) {
        String email = user.getEmail();
        NotificationDtoRequest notificationRequest = new NotificationDtoRequest(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Error to send notification");
            throw new NotificationServiceOutException("Notification Service is out");
        }

        System.out.println("Notification was sent to the user");
    }
}
