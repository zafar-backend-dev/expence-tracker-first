package com.example.test.dto;


public class ApiResponse<T> {
    private boolean success;
    private String messageEn;
    private String messageTr;
    private T data;

    public ApiResponse(boolean success, String messageEn, String messageTr) {
        this.success = success;
        this.messageEn = messageEn;
        this.messageTr = messageTr;
    }

    public ApiResponse(boolean success, String messageEn, String messageTr, T data) {
        this.success = success;
        this.messageEn = messageEn;
        this.messageTr = messageTr;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public void setMessageEn(String messageEn) {
        this.messageEn = messageEn;
    }

    public String getMessageTr() {
        return messageTr;
    }

    public void setMessageTr(String messageTr) {
        this.messageTr = messageTr;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
