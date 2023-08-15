package com.app.travelx.sms;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service("twilio")
public class TwilioSMSSender implements SMSSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSMSSender.class);
    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSMSSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSMS(SMSRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}" + smsRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] not a valid number"
            );
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
//        String digitsOnly = phoneNumber.replaceAll("\\D", "");
//        // The most common regular expression for UK phone number validation
//        String regex = "^(?:\\+44|0044|0)?[1-9][0-9]{8,9}$";

//        return Pattern.matches(regex, digitsOnly);
        return true;
    }
}


