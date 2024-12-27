package com.sijibomiaol.skaetAss.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
@Data

public class UserAccountRequest implements Serializable {
    private Long id;
    @NotNull(message = "Please put your firt name ")
    private String firstName;
    @NotNull(message = "Please put your last name/ surname")
    private String lastName;
    @NotNull(message = "Enter your phoneNumber")
    private String phoneNumber;
    @Size(min=4, max = 4, message = "The pin has to be 4")
    @NotNull(message = "Add your Pin" )
    private String pin;
}
