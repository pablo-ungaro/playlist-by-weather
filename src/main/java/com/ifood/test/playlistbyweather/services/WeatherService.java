package com.ifood.test.playlistbyweather.services;


import com.ifood.test.playlistbyweather.model.OpenWeatherMap;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class WeatherService {
    @Value("${api.weather.host}")
    private String weatherApiHost;
    @Value("${api.weather.appId}")
    private String appId;
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);


    public Double getTemperatureFromPlace(String city, Double lat, Double lon){
        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherMap weather= null;
        try {
            weather = restTemplate.getForObject(getURI(city,lat,lon), OpenWeatherMap.class);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }

        log.info(String.format("Temperatura no local pesquisado: %s",weather.getTemperature()));

        return weather.getTemperature();
    }

    private URI getURI(String city, Double lat, Double lon) throws URISyntaxException {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost(weatherApiHost)
                .setPath("/data/2.5/weather")
                .setParameter("APPID", appId)
                .setParameter("units", "metric");
        if(city != null )builder.setParameter("q", city);
        if(lat != null && lon != null) builder.setParameter("lat", lat.toString()).setParameter("lon", lon.toString());

        return builder.build();
    }

}
