package ua.com.owu.sep2021javaadv.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.owu.sep2021javaadv.customErrors.CustomError;
import ua.com.owu.sep2021javaadv.models.dto.ErrorDTO;

@RestControllerAdvice
public class ErrorController {


    @ExceptionHandler({CustomError.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO aexcpertion(CustomError e) {
        return new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());

    }


}
