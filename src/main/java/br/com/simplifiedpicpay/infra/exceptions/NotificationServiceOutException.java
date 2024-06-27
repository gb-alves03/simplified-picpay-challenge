package br.com.simplifiedpicpay.infra.exceptions;

public class NotificationServiceOutException extends RuntimeException {
    public NotificationServiceOutException(String message) {
        super(message);
    }
}
