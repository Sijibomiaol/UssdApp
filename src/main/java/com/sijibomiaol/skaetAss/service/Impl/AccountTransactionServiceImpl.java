package com.sijibomiaol.skaetAss.service.Impl;

import com.sijibomiaol.skaetAss.context.entity.AccountDetails;
import com.sijibomiaol.skaetAss.context.entity.UserDetails;
import com.sijibomiaol.skaetAss.context.repository.AccountDetailsRepository;
import com.sijibomiaol.skaetAss.context.repository.UserDetailsRepository;
import com.sijibomiaol.skaetAss.exception.CustomException;
import com.sijibomiaol.skaetAss.messaging.kafka.SmsService;
import com.sijibomiaol.skaetAss.model.request.SmsModel;
import com.sijibomiaol.skaetAss.service.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {

    private final AccountDetailsRepository accountDetailsRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final SmsService smsService;

    @Override
    @Caching(
            put = {@CachePut(value = "accountDetailsCache", key = "#phoneNumber")},
            evict = {@CacheEvict(value = "allAccountDetailsCache", allEntries = true)}
    )

    public BigDecimal deposit(BigDecimal amount, String phoneNumber) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByPhoneNumber(phoneNumber);
        if (userDetails.isPresent()) {
            UserDetails userDetail = userDetails.get();
            Optional<AccountDetails> optionalAccountDetails =accountDetailsRepository.findByAccountNumber(userDetail.getAccountDetails().getAccountNumber());
            if (optionalAccountDetails.isPresent()) {
                AccountDetails accountDetails = optionalAccountDetails.get();
                BigDecimal balance = accountDetails.getBalance().add(amount);
                accountDetails.setBalance(balance);
                accountDetailsRepository.save(accountDetails);
                smsService.sendSms(new SmsModel("", Collections.singletonList(userDetail.getPhoneNumber()), "Dear Customer, your account has been credited with " + amount + ". Your new balance is " + accountDetails.getBalance()));

                return accountDetails.getBalance();
            }

        }
        throw new CustomException("User Details can not be found ");
    }
    @Override
    @Caching(
            put = {@CachePut(value = "accountDetailsCache", key = "#phoneNumber")},
            evict = {@CacheEvict(value = "allAccountDetailsCache", allEntries = true)}
    )
    public BigDecimal withdraw(BigDecimal amount, String phoneNumber) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByPhoneNumber(phoneNumber);
        if (userDetails.isPresent()) {
            UserDetails userDetail = userDetails.get();
            Optional<AccountDetails> optionalAccountDetails =accountDetailsRepository.findByAccountNumber(userDetail.getAccountDetails().getAccountNumber());
            if (optionalAccountDetails.isPresent()) {
                AccountDetails accountDetails = optionalAccountDetails.get();
                if (accountDetails.getBalance().compareTo(amount) > 0) {
                    BigDecimal balance = accountDetails.getBalance().subtract(amount);
                    accountDetails.setBalance(balance);
                    accountDetailsRepository.save(accountDetails);
                    smsService.sendSms(new SmsModel("", Collections.singletonList(userDetail.getPhoneNumber()), "Dear Customer, your account has been debited with " + amount + ". Your new balance is " + accountDetails.getBalance()));
                    return accountDetails.getBalance();
                }
                else {
                    throw new CustomException("Balance can not be negative");
                }
            }
        }
        throw new CustomException("User Details can not be found ");
    }
    @Override
    @Caching(
            put = {@CachePut(value = "accountDetailsCache", key = "#phoneNumber")},
            evict = {@CacheEvict(value = "allAccountDetailsCache", allEntries = true)}
    )
    public BigDecimal checkBalance(String phoneNumber) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByPhoneNumber(phoneNumber);
        if (userDetails.isPresent()) {
            UserDetails userDetail = userDetails.get();
            Optional<AccountDetails> optionalAccountDetails =accountDetailsRepository.findByAccountNumber(userDetail.getAccountDetails().getAccountNumber());
            if (optionalAccountDetails.isPresent()) {
                AccountDetails accountDetails = optionalAccountDetails.get();
                BigDecimal balance = accountDetails.getBalance();
                smsService.sendSms(new SmsModel("", Collections.singletonList(userDetail.getPhoneNumber()), "Dear Customer, your account balance is " + balance));
                return accountDetails.getBalance();
            }
        }
        throw new CustomException("User Details can not be found ");
    }


}
