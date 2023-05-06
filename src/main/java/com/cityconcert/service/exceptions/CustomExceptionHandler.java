package com.cityconcert.service.exceptions;

import com.cityconcert.domain.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException notFoundException){

        ResponseDTO response = new ResponseDTO();
        if (notFoundException.getMessage()!=null) response.setMessage(notFoundException.getMessage());
        else response.setMessage("Entity Not Found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    protected ResponseEntity<ResponseDTO> handleEmailAlreadyUsedException(EmailAlreadyUsedException e){
        ResponseDTO response = new ResponseDTO();
        if (e.getMessage()!=null) response.setMessage(e.getMessage());
        else response.setMessage("Authorization error");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<ResponseDTO> handleInvalidPasswordException(
            InvalidPasswordException e){
        ResponseDTO response = new ResponseDTO();
        if (e.getMessage()!=null) response.setMessage(e.getMessage());
        else response.setMessage("Authorization error");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    protected ResponseEntity<ResponseDTO> handleUsernameAlreadyUsedException(
            UsernameAlreadyUsedException e){
        ResponseDTO response = new ResponseDTO();
        if (e.getMessage()!=null) response.setMessage(e.getMessage());
        else response.setMessage("Authorization error");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}