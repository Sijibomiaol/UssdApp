package com.sijibomiaol.skaetAss.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class UssdResponse implements Serializable {
private Map<String, String> message;
}
