package com.sijibomiaol.skaetAss.service.Impl;

import com.sijibomiaol.skaetAss.context.entity.AccountDetails;
import com.sijibomiaol.skaetAss.context.entity.UserDetails;
import com.sijibomiaol.skaetAss.context.repository.AccountDetailsRepository;
import com.sijibomiaol.skaetAss.context.repository.UserDetailsRepository;
import com.sijibomiaol.skaetAss.exception.CustomException;
import com.sijibomiaol.skaetAss.messaging.kafka.SmsService;
import com.sijibomiaol.skaetAss.model.request.UserAccountRequest;
import com.sijibomiaol.skaetAss.model.response.UserDetailsResponse;
import com.sijibomiaol.skaetAss.model.request.SmsModel;
import com.sijibomiaol.skaetAss.service.UserAccountService;
import com.sijibomiaol.skaetAss.utills.AccountGenerator;
import com.sijibomiaol.skaetAss.utills.ModelMapperUtils;
import com.sijibomiaol.skaetAss.utills.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final SmsService smsService;

    @Override
    @Transactional
    @Caching(
            put = {@CachePut(value = "allUserDetailsCache", key = "#result.id")},
            evict = {@CacheEvict(value = "allUserDetailsCache", allEntries = true)}
    )
    public UserDetailsResponse createUserAccount(UserAccountRequest request) {
        Optional<UserDetails> existingUser = userDetailsRepository.findByPhoneNumber(request.getPhoneNumber());
        if (existingUser.isPresent()) {
            throw  new CustomException("User already exists");
        }

        UserDetails newUser = new UserDetails();
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setPin(SecurityUtils.twoXEncryptStringData(request.getPin()));

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountNumber(AccountGenerator.generateAccountId());
        accountDetails.setBalance(BigDecimal.ZERO);
        accountDetailsRepository.save(accountDetails);
        newUser.setAccountDetails(accountDetails);
        userDetailsRepository.save(newUser);
        smsService.sendSms(
                new SmsModel(
                        "",
                        Collections.singletonList(newUser.getPhoneNumber()),
                        "Dear " + newUser.getFirstName() + " " + newUser.getLastName()
                                + "\n\nYour account has been created.\n\nAccount number: "
                                + newUser.getAccountDetails().getAccountNumber()
                                + "\n\nThank you."
                )
        );


        return ModelMapperUtils.map(newUser, UserDetailsResponse.class);



    }
    @Override
    @Cacheable(value = "UserDetailsCache", key = "#phoneNumber")
    public UserDetailsResponse findByPhoneNumber(String phoneNumber) {
        Optional<UserDetails> existingUser = userDetailsRepository.findByPhoneNumber(phoneNumber);
        if (existingUser.isPresent()) {
            UserDetails userDetails = existingUser.get();
            return ModelMapperUtils.map(userDetails, UserDetailsResponse.class);
        }
        throw  new CustomException("User not found");
    }
}
