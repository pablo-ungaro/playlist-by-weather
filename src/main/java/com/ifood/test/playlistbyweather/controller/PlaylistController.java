package com.ifood.test.playlistbyweather.controller;

import com.ifood.test.playlistbyweather.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("playlist")
public class PlaylistController {
    @Autowired private PlaylistService playlistService;

    @GetMapping
    public List<String> suggest(@RequestParam(required = false,defaultValue = "") String city,
                                @RequestParam(required = false) Double latitude,
                                @RequestParam(required = false) Double longitude,
                                @RequestParam(required = false, defaultValue = "50") Integer max,
                                @RequestParam(required = false, defaultValue = "0") Integer offset){

        return playlistService.getPlaylistByWheather(city,latitude,longitude,Math.min(max,50),offset);
    }
}
