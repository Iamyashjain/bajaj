package com.chitkara.bfhl.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private Boolean is_success;
    private String official_email;
    private T data;
    private String error;

    // No Args Constructor
    public ApiResponse() {
    }

    // All Args Constructor
    public ApiResponse(Boolean is_success, String official_email, T data, String error) {
        this.is_success = is_success;
        this.official_email = official_email;
        this.data = data;
        this.error = error;
    }

    // Custom Constructor
    public ApiResponse(Boolean isSuccess, String email, T data) {
        this.is_success = isSuccess;
        this.official_email = email;
        this.data = data;
    }

    // Getters and Setters
    public Boolean getIs_success() {
        return is_success;
    }

    public void setIs_success(Boolean is_success) {
        this.is_success = is_success;
    }

    public String getOfficial_email() {
        return official_email;
    }

    public void setOfficial_email(String official_email) {
        this.official_email = official_email;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // toString
    @Override
    public String toString() {
        return "ApiResponse{" +
                "is_success=" + is_success +
                ", official_email='" + official_email + '\'' +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }

    // Static Success Response
    public static <T> ApiResponse<T> success(String email, T data) {
        return new ApiResponse<>(true, email, data, null);
    }

    // Static Error Response
    public static <T> ApiResponse<T> error(String email, String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setIs_success(false);
        response.setOfficial_email(email);
        response.setError(error);
        return response;
    }
}