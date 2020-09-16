package com.cicklum.paperrockscissor.controller.poji;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;

public interface ExceptionController {
    ResponseEntity getUserDuplicatedException(UserDuplicatedException ex);

//    ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex);
}
