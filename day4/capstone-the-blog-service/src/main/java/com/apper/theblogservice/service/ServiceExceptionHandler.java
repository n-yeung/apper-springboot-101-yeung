package com.apper.theblogservice.service;

import com.apper.theblogservice.Payload.BloggerDetails;
import com.apper.theblogservice.Payload.ServiceError;
import com.apper.theblogservice.exceptions.blogNotFoundException;
import com.apper.theblogservice.exceptions.bloggerListException;
import com.apper.theblogservice.exceptions.emailAlreadyUsedException;
import com.apper.theblogservice.exceptions.invalidBloggerIdException;
import com.apper.theblogservice.model.Blogger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice //all exceptions will be handled by this
public class ServiceExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ServiceError handleInvalidInputFields(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(objectError -> new ServiceError(objectError.getDefaultMessage()))
                .orElse(new ServiceError("Unknown invalid argument encountered"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(emailAlreadyUsedException.class)
    @ResponseBody
    public ServiceError emailIsRegisteredException(emailAlreadyUsedException exception) {
        return new ServiceError(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(invalidBloggerIdException.class)
    @ResponseBody
    public ServiceError invalidIdException(invalidBloggerIdException exception) {
        return new ServiceError(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(bloggerListException.class)
    @ResponseBody
    public ServiceError bloggerListEmpty(bloggerListException exception) {
        return new ServiceError(exception.getMessage());
    }

    @ExceptionHandler(blogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ServiceError BlogNotFoundException(blogNotFoundException exception) {
        return new ServiceError(exception.getMessage());
    }

}
