package com.ifood.test.playlistbyweather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SpotifyIntegrationException extends RuntimeException{
    public SpotifyIntegrationException(String message){
      super(message);
    }
}
