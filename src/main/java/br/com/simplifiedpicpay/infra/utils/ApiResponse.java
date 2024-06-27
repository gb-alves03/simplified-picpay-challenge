package br.com.simplifiedpicpay.infra.utils;

public class ApiResponse {
    private String status;
    private DataResponse data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }
}
