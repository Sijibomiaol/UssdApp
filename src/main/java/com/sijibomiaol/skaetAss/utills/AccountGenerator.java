package com.sijibomiaol.skaetAss.utills;

import java.time.Year;

public class AccountGenerator {
    public static String generateAccountId(){
        Year year = Year.now();
        int min =100000;
        int max = 999999;

        double random = (int)Math.floor(Math.random() * (max - min + 1)) + min;
        String randomNumber = String.valueOf(random);
        StringBuilder  accountNumber = new StringBuilder();
        accountNumber.append(year).append(randomNumber);
        return accountNumber.toString();

    }
}
