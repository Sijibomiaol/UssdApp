package com.sijibomiaol.skaetAss.messaging.kafka;

import com.sijibomiaol.skaetAss.model.request.SmsModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final RestTemplate restTemplate;
    private String accessKey = "Ak2NPcmNEq4p3GCfK2Ecv6tQA68nslopTnnNZhF91XSz5NgdrYylS855rbc1";
    private String baseUrl = "https://www.bulksmsnigeria.com/api/v1/sms/create";

    @Override
    public String sendSms(SmsModel smsModel) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String connection=baseUrl+"?api_token="+accessKey+"&from=BulkSMS.ng&to="+smsModel.getTo()+"&body="+smsModel.getText()+"&dnd=2";

        ResponseEntity<String> response= restTemplate.exchange(connection, HttpMethod.GET, entity, String.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return "Message sent";
        }else{
            return "Unable to to send Message";
        }
    }

}
