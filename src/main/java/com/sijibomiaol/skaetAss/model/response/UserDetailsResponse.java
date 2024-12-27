package com.sijibomiaol.skaetAss.model.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserDetailsResponse implements Serializable {
    private Long id;

    private String firstName;

    private String lastName;

    private String telephone;

    private String email;

    private AccountDetailsResponse accountDetails;
}
