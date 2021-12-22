package pl.partabelo.demo.controllers;

import com.sun.mail.iap.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.Models.DeviceModel.DeviceToSend;
import pl.partabelo.demo.services.DeviceService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/spotify")
public class DeviceController {

    final
    DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }



    @GetMapping("/device")
    public ResponseEntity<?> getDevice(){
        return new ResponseEntity<>(deviceService.getCurrentDevice(), HttpStatus.OK);
    }

    @PostMapping("/device")
    @ResponseBody
    public ResponseEntity<?> setDevice(@RequestBody DeviceToSend device){
        this.deviceService.setDevice(device);
        System.out.println(this.deviceService.getCurrentDevice());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/device/all")
    public ResponseEntity<?> getDevices(){
        return new ResponseEntity<>(deviceService.getDevices(), HttpStatus.OK);
    }
}
