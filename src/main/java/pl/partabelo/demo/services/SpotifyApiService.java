package pl.partabelo.demo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.partabelo.demo.Models.DeviceModel.DeviceToSend;

@Service
public class SpotifyApiService {

    private String jwt;
    private DeviceToSend currentDevice = new DeviceToSend("8bf9ebb09ae56bea6ac31393315d97ef49581af5","iMac (Adrian)","Computer");

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
        AuthorizationCodeRefreshExample.authorizationCodeRefresh_Async();
        this.jwt = AuthorizationCodeRefreshExample.spotifyApi.getAccessToken();
    }


    public String getJwt() {
        return jwt==null?null:jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public DeviceToSend getCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(DeviceToSend currentDevice) {
        this.currentDevice = currentDevice;
    }

}
