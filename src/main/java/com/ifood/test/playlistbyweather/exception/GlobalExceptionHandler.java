package com.ifood.test.playlistbyweather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object>  handleAllException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OpenWeatherMapsIntegrationException.class)
    public final ResponseEntity<Object>  handleWeatherException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpotifyIntegrationException.class)
    public final ResponseEntity<Object> handleSpotifyException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}