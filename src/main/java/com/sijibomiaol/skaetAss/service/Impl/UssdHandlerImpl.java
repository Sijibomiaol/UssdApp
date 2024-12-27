package com.sijibomiaol.skaetAss.service.Impl;

import com.sijibomiaol.skaetAss.model.request.UserAccountRequest;
import com.sijibomiaol.skaetAss.model.request.UssdModel;
import com.sijibomiaol.skaetAss.model.request.UssdRequest;
import com.sijibomiaol.skaetAss.model.response.UssdResponse;
import com.sijibomiaol.skaetAss.service.AccountTransactionService;
import com.sijibomiaol.skaetAss.service.UserAccountService;
import com.sijibomiaol.skaetAss.service.UssdHandler;
import com.sijibomiaol.skaetAss.utills.AppUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UssdHandlerImpl implements UssdHandler {
    private final AccountTransactionService accountTransactionService;
    private final UserAccountService userAccountService;
    private final UssdModel ussdModel;

    private static final String INIT_CODE = "*123#";

    @Override
    public UssdResponse processRequest(UssdRequest request, HttpServletRequest servletRequest) {
        if(request.getCode() != null){
                if (ussdModel.getInit() != null && ussdModel.getInit().equals(INIT_CODE)) {
                    init();
                    log.info("entered ==> {}", AppUtils.toJson(init()));
                    request =generateUssdRequest(request.getCode(), request.getPhoneNumber(), request.getPin());
                    if (request.getCode().equals(ussdModel.getCreate())){
                        confirm();
                        request = generateUssdRequest(request.getCode(), request.getPhoneNumber(), request.getPin());
                        UserAccountRequest userAccountRequest = new UserAccountRequest();
                        userAccountRequest.setFirstName(servletRequest.getParameter("firstName"));
                        userAccountRequest.setLastName(servletRequest.getParameter("lastName"));
                        userAccountRequest.setPin(request.getPin());
                        userAccountRequest.setPhoneNumber(request.getPhoneNumber());
                        userAccountService.createUserAccount(userAccountRequest);
                    }
                    else if(request.getCode().equals(ussdModel.getDeposit())) {
                        confirm();
                        request = generateUssdRequest(request.getCode(), request.getPhoneNumber(), request.getPin());
                        accountTransactionService.deposit(BigDecimal.valueOf(Double.parseDouble(servletRequest.getParameter("amount"))),request.getPhoneNumber());
                    }
                    else if(request.getCode().equals(ussdModel.getWithdraw())) {
                        confirm();
                        request = generateUssdRequest(request.getCode(), request.getPhoneNumber(), request.getPin());
                        accountTransactionService.withdraw(BigDecimal.valueOf(Double.parseDouble(servletRequest.getParameter("amount"))),request.getPhoneNumber());
                    }
                    else if (request.getCode().equals(ussdModel.getBalance())){
                        confirm();
                        request = generateUssdRequest(request.getCode(), request.getPhoneNumber(), request.getPin());
                        accountTransactionService.checkBalance(request.getPhoneNumber());
                    }

                }
        }
        return null;
    }

    UssdResponse init() {
        UssdResponse response = new UssdResponse();
        Map<String, String> map = new HashMap<>();
        map.put("1", "Create Account");
        map.put("2", "Account Deposit");
        map.put("3", "Account Withdrawal");
        map.put("4", "Account Balance");
        response.setMessage(map);
        return response;
    }

    private UssdRequest generateUssdRequest(String code, String telephone, String pin) {
        UssdRequest ussdRequest = new UssdRequest();
        ussdRequest.setCode(code);
        ussdRequest.setPin(pin);
        ussdRequest.setPhoneNumber(telephone);
        return ussdRequest;
    }

    UssdResponse confirm() {
        UssdResponse response = new UssdResponse();
        Map<String, String> map = new HashMap<>();
        map.put("1", "Confirm");
        map.put("9", "Back");
        map.put("0", "Exit");
        response.setMessage(map);
        return response;
    }
}
