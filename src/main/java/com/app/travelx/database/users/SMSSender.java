package com.app.travelx.database.users;

public interface SMSSender {
    void sendSMS(SMSRequest smsRequest);
}
