package pl.partabelo.demo.controllers;

import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.services.DeviceService;
import org.springframework.stereotype.Controller;

@Controller
@RestController
@RequestMapping("/spotify")
public class DeviceController {

    final
    DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/device")
    public String getDevices(){
        return deviceService.getDevices();
    }

    @GetMapping("/device/{device}")
    @ResponseBody
    public void setDevice(@PathVariable String device){
        this.deviceService.setDevice(device);
    }
}
