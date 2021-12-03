package pl.partabelo.demo.services;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    final
    SpotifyApiService spotifyApiService;

    public DeviceService(SpotifyApiService spotifyApiService) {
        this.spotifyApiService = spotifyApiService;
    }

    public void setDevice(String deviceUri){
        this.spotifyApiService.setCurrentDevice(deviceUri);
    }
    public String getDevices(){
        return this.spotifyApiService.templator(
                "https://api.spotify.com/v1/me/player/devices",
                HttpMethod.GET,
                String.class
                );
    }
}
