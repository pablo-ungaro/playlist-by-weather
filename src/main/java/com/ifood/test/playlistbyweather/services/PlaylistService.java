package com.ifood.test.playlistbyweather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private SpotifyService spotifyService;

    public List<String> getPlaylistByWheather(String city, Double lat, Double lon,Integer max, Integer offset) {
        Double temperature = weatherService.getTemperatureFromPlace(city, lat, lon);
        String genre = calculateGenre(temperature);
        List<String> playlist = spotifyService.getPlaylist(genre,max,offset);

        return playlist;
    }

    private String calculateGenre(Double temperature) {
        String genre = "classical";

        if (temperature > 30) {
            genre = "party";
        } else if (temperature <= 30 && temperature >= 15) {
            genre = "pop";
        } else if (temperature <= 14 && temperature >= 10) {
            genre = "rock";
        }

        return genre;
    }
}
