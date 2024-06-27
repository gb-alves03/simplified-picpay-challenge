package br.com.simplifiedpicpay.infra.utils;

public class DataResponse {

    private boolean authorization;

    public boolean isAuthorized() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        this.authorization = authorization;
    }
}
