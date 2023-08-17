package com.app.travelx.database.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
class SMSService {
    private final SMSSender smsSender;
    @Autowired
    public SMSService(@Qualifier("twilio") TwilioSMSSender smsSender) {
            this.smsSender = smsSender;
    }
    public void sendSMS(SMSRequest smsRequest) {
            smsSender.sendSMS(smsRequest);
    }
}
