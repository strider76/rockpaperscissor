package com.cicklum.paperrockscissor.controller.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cicklum.paperrockscissor.controller.poji.ExceptionController;
import com.cicklum.paperrockscissor.exception.AccessDeniedException;
import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.dto.ErrorMessagesDto;

@RestControllerAdvice
@Validated
public class ExceptionControllerImpl extends ResponseEntityExceptionHandler implements ExceptionController{

    @Override
    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity getUserDuplicatedException(UserDuplicatedException ex) {
	List<ErrorMessagesDto> errorsList = new ArrayList<>();
	errorsList.add(new ErrorMessagesDto(ex.getMessage()));
	return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDeniedException(AccessDeniedException e) {
	return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	BindingResult result = ex.getBindingResult();

	List<ErrorMessagesDto> errorList = new ArrayList<>();

	result.getFieldErrors().forEach(fieldError ->
	    errorList.add(new ErrorMessagesDto(fieldError.getField()+" : " +fieldError.getDefaultMessage()))
	);

	return new ResponseEntity(errorList, HttpStatus.BAD_REQUEST);
    }
}
