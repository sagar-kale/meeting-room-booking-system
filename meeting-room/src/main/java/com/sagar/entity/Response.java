package com.sagar.entity;

public class Response {
    private String statusText;
    private Integer status;

    public Response() {
    }

    public Response(String statusText, Integer status) {
        this.statusText = statusText;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusText='" + statusText + '\'' +
                ", status=" + status +
                '}';
    }
}
