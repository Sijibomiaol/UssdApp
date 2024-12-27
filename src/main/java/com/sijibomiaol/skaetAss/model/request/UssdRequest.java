package com.sijibomiaol.skaetAss.model.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UssdRequest {
    private String code;
    private String phoneNumber;
    private String pin;
}
