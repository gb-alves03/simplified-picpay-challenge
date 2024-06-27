package br.com.simplifiedpicpay.services;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotificationMessage implements Serializable {
    private String email;
    private String message;

    public NotificationMessage(String email, String message) {
        this.email = email;
        this.message = message;
    }
}
