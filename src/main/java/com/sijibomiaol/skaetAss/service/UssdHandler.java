package com.sijibomiaol.skaetAss.service;

import com.sijibomiaol.skaetAss.model.request.UssdRequest;
import com.sijibomiaol.skaetAss.model.response.UssdResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UssdHandler {
    UssdResponse processRequest(UssdRequest request, HttpServletRequest servletRequest);
}
