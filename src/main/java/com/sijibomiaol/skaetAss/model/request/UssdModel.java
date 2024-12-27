package com.sijibomiaol.skaetAss.model.request;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ussd.service.code")
@Data
public class UssdModel {

private String exit;
private String previous;
private String init;
private String create;
private String deposit;
private String withdraw;
private String balance;
}
