package pl.partabelo.demo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyApiService {

    private String jwt;
    private String currentDevice = "8bf9ebb09ae56bea6ac31393315d97ef49581af5";

    public <T> T templator(String url, HttpMethod httpMethod, Class<T> classType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<T> exchange = restTemplate.exchange(url,
                httpMethod,
                httpEntity,
                classType);
        return exchange.getBody();
    }
    public void setJwtAuto(){
        AuthorizationCodeRefreshExample.authorizationCodeRefresh_Sync();
        this.jwt = AuthorizationCodeRefreshExample.spotifyApi.getAccessToken();
    }


    public String getJwt() {
        return jwt==null?null:jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(String currentDevice) {
        this.currentDevice = currentDevice;
    }

}
