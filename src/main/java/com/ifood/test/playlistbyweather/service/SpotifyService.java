package com.ifood.test.playlistbyweather.service;

import com.ifood.test.playlistbyweather.exception.SpotifyIntegrationException;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpotifyService {
    @Value("${api.spotify.clientId}")
    private String clientId;
    @Value("${api.spotify.secretId}")
    private String secretId;

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(secretId)
            .build();
    private final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

    public List<String> getPlaylist(String genre,Integer max,Integer offset){
        final Paging<Track> trackPaging;
        List<String> result;
        try {
            setCredentialsHeaders();
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks("genre:"+genre)
                    .market(CountryCode.BR)
                    .limit(max)
                    .offset(offset)
                    .build();
            trackPaging = searchTracksRequest.execute();
            result = Arrays.stream(trackPaging.getItems()).map(Track::getName).collect(Collectors.toList());


            log.info(String.format("Resultado para %s: %d",genre,trackPaging.getTotal()));
        } catch (IOException | SpotifyWebApiException e) {
            log.error(e.getMessage());
            throw  new SpotifyIntegrationException(e.getMessage());
        }
        return result;
    }

    //This library stop works with no reason, this is a temporary workaround ;(
    private void setCredentialsHeaders() throws UnsupportedEncodingException {
        clientCredentialsRequest.getHeaders().clear();
        String credentials = clientId + ":" + secretId;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes("utf-8"));
        clientCredentialsRequest.getHeaders().add(new BasicHeader("Content-Type","application/x-www-form-urlencoded"));
        clientCredentialsRequest.getHeaders().add(new BasicHeader("Authorization",String.format("Basic %s",encoded)));
    }
}
