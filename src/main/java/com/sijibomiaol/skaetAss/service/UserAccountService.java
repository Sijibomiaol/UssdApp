package com.sijibomiaol.skaetAss.service;

import com.sijibomiaol.skaetAss.model.request.UserAccountRequest;
import com.sijibomiaol.skaetAss.model.response.UserDetailsResponse;

public interface UserAccountService {

    UserDetailsResponse createUserAccount(UserAccountRequest request);

    UserDetailsResponse findByPhoneNumber(String phoneNumber);
}
