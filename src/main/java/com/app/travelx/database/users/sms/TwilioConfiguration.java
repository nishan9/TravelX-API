package com.app.travelx.database.users.sms;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

    private String accountSid;

    private String authToken;

    private String trialNumber;

}
