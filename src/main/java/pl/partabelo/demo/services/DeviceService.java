package pl.partabelo.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import pl.partabelo.demo.Models.DeviceModel.Device;
import pl.partabelo.demo.Models.DeviceModel.DeviceToSend;
import pl.partabelo.demo.Models.DeviceModel.Devices;
import pl.partabelo.demo.repository.DeviceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {
    final
    SpotifyApiService spotifyApiService;

    final
    DeviceRepository deviceRepository;

    public DeviceService(SpotifyApiService spotifyApiService, DeviceRepository deviceRepository) {
        this.spotifyApiService = spotifyApiService;
        this.deviceRepository = deviceRepository;
    }

    public void setDevice(DeviceToSend device){
        this.spotifyApiService.setCurrentDevice(device);
    }

    public void setDevicesToList(){
        List<DeviceToSend> devicesList = new ArrayList<>();
       Devices devices = this.spotifyApiService.templator(
                "https://api.spotify.com/v1/me/player/devices",
                HttpMethod.GET,
                Devices.class
                );
        for (Device device : devices.getDevices()) {
            devicesList.add(new DeviceToSend(device.getId(),device.getName(),device.getType()));
        }
        this.deviceRepository.setDevices(devicesList);
    }

    public DeviceToSend getCurrentDevice(){
        return this.spotifyApiService.getCurrentDevice();
    }

    public List<DeviceToSend> getDevices(){
        this.setDevicesToList();
        return this.deviceRepository.getDevices();
    }

    public void setDevices(List<DeviceToSend> devices){
        this.deviceRepository.setDevices(devices);
    }

}
