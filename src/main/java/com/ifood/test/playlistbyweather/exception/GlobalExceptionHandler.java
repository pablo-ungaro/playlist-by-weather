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
    public final ResponseEntity<ExceptionResponse>  handleAllException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OpenWeatherMapsIntegrationException.class)
    public final ResponseEntity<ExceptionResponse>  handleWeatherException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpotifyIntegrationException.class)
    public final ResponseEntity<ExceptionResponse> handleSpotifyException(Exception ex, WebRequest request) throws Exception {
        return new ResponseEntity(new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}