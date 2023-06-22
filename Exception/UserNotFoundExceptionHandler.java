package com.spring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;


@RestControllerAdvice
public class UserNotFoundExceptionHandler{
	
	
	@ExceptionHandler(value= {UserNotFoundException.class,HttpMessageNotWritableException.class})
     
	public ResponseEntity<ResponseMessage> handleUserNotFoundExceprion(UserNotFoundException ex)
	{
		ResponseMessage rm =new ResponseMessage((ex.getMessage()));
		
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND );
		
	}
	@ExceptionHandler(InternalServerError.class)
    
	public ResponseEntity<ResponseMessage> handleUserNotFoundExceprion2(UserNotFoundException ex)
	{
		ResponseMessage rm =new ResponseMessage((ex.getMessage()));
		
		
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.NOT_FOUND );
		
	}
}
