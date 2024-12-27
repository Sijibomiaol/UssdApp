package com.sijibomiaol.skaetAss.messaging.kafka;

import com.sijibomiaol.skaetAss.model.request.SmsModel;

public interface SmsService {
    String sendSms(SmsModel smsModel);
}
