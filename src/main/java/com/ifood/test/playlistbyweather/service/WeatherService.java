package com.ifood.test.playlistbyweather.service;


import com.ifood.test.playlistbyweather.exception.OpenWeatherMapsIntegrationException;
import com.ifood.test.playlistbyweather.model.OpenWeatherMap;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class WeatherService {
    @Value("${api.weather.host}")
    private String weatherApiHost;
    @Value("${api.weather.appId}")
    private String appId;
    @Value("${api.weather.expire.temperature}")
    private Integer expireTemperature;
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    @Autowired
    private RedisService redisService;


    public Double getTemperatureFromPlace(String city, Double lat, Double lon) {
        String cacheKey = String.format("%s%s%s",city,lat==null?"":lat,lon==null?"":lon);
        Optional fromCache = redisService.getFromCache(cacheKey);

        if (fromCache.isPresent()) return (Double) fromCache.get();

        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherMap weather;
        try {
            weather = restTemplate.getForObject(getURI(city,lat,lon), OpenWeatherMap.class);
            redisService.setInCache(cacheKey,weather.getTemperature(),expireTemperature);
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
            redisService.remove(cacheKey);
            throw new OpenWeatherMapsIntegrationException(e.getMessage());
        }catch (Exception ex){
            log.error(ex.getMessage());
            redisService.remove(cacheKey);
            throw new OpenWeatherMapsIntegrationException(ex.getMessage());
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
