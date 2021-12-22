package pl.partabelo.demo.repository;

import org.springframework.stereotype.Repository;
import pl.partabelo.demo.Models.DeviceModel.DeviceToSend;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeviceRepository {
    List<DeviceToSend> devices = new ArrayList<>();

    public List<DeviceToSend> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceToSend> devices) {
        this.devices = devices;
    }
}
