package com.sijibomiaol.skaetAss.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsModel implements Serializable {
    @NotNull
    @Size(min = 1, max = 11)
    private String from;
    @NotNull
    private List<String> to = new ArrayList<>();
    @NotNull
    private String text;
}
