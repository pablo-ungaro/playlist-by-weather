package com.ifood.test.playlistbyweather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OpenWeatherMapsIntegrationException extends RuntimeException{
    public OpenWeatherMapsIntegrationException(String message){
      super(message);
    }
}
