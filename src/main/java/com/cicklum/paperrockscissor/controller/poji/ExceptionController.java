package com.cicklum.paperrockscissor.controller.poji;

import org.springframework.http.ResponseEntity;

import com.cicklum.paperrockscissor.exception.AccessDeniedException;
import com.cicklum.paperrockscissor.exception.UserDuplicatedException;

public interface ExceptionController {
    ResponseEntity getUserDuplicatedException(UserDuplicatedException ex);

    ResponseEntity accessDeniedException(AccessDeniedException e);

}
