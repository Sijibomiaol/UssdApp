package com.sijibomiaol.skaetAss.controller;

import com.sijibomiaol.skaetAss.model.request.UserAccountRequest;
import com.sijibomiaol.skaetAss.model.response.UserDetailsResponse;
import com.sijibomiaol.skaetAss.model.request.UssdRequest;
import com.sijibomiaol.skaetAss.model.response.UssdResponse;
import com.sijibomiaol.skaetAss.service.AccountTransactionService;
import com.sijibomiaol.skaetAss.service.UserAccountService;
import com.sijibomiaol.skaetAss.service.UssdHandler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {


    private final UserAccountService userAccountService;
    private final AccountTransactionService transactionService;
    private final UssdHandler ussdServiceHandler;

    @PostMapping("/create")
    public ResponseEntity<UserDetailsResponse>createUserAccount(@RequestBody UserAccountRequest request) {
        UserDetailsResponse response = userAccountService.createUserAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/deposit")
    public ResponseEntity<BigDecimal> deposit(@RequestParam BigDecimal amount, @RequestParam String phoneNumber) {
        BigDecimal response = transactionService.deposit(amount, phoneNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/withdraw")
    public ResponseEntity<BigDecimal> withdraw(@RequestParam BigDecimal amount, @RequestParam String phoneNumber) {
        BigDecimal response = transactionService.withdraw(amount, phoneNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam String phoneNumber) {
        BigDecimal response = transactionService.checkBalance(phoneNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/ussd")
    public ResponseEntity<?> handler(@RequestBody UssdRequest ussdRequest, HttpServletRequest request) {
        UssdResponse response= ussdServiceHandler.processRequest(ussdRequest, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





}
