package com.app.travelx.database.users.sms;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class SMSRequest {

    @NotBlank
    private final String phoneNumber;
    @NotBlank
    private final String message;

    public SMSRequest(@JsonProperty("phoneNumber") String phoneNumber,@JsonProperty("message") String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SMSRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
