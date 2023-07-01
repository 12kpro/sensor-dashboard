package capstone.fe_api.dataservice.controllers;

import capstone.fe_api.dataservice.dto.Sensor;
import capstone.fe_api.dataservice.dto.SensorCreateDto;
import capstone.fe_api.exceptions.BadRequestException;
import capstone.fe_api.exceptions.NotFoundException;
import capstone.fe_api.utils.HeadersInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/sensor")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
public class SensorController {
    @Value("${service.url}")
    private String serviceUrl;
    @Value("${service.port}")
    private int servicePort;

//    @GetMapping("")
//    public Page<Sensor> getSensors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
//                                   @RequestParam(defaultValue = "id") String sortBy) {
//
//        RestTemplate restTemp1ate = new RestTemplate();
//        BannedUserPayLoad response = restTemp1ate.getForObject ("http://localhost:3002/bannedUsers/" + body.getEmail(),BannedUserPayload.class);
//        return sensorService.find(page, size, sortBy);
//    }
    @GetMapping("/{id}")
    public Sensor getSensor(@PathVariable UUID id) throws NotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new HeadersInterceptor());
        Sensor response = restTemplate.getForObject (serviceUrl + ":" + servicePort + "/sensor/" + id,Sensor.class);

        if (response != null) {
            throw new BadRequestException("L' utente é giå stato bannato perché" + response.getReason()) ;
        }
        return sensorService.findById(id);
    }

//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Sensor saveSensor(@RequestBody @Validated SensorCreateDto body) {
//        return sensorService.create(body);
//    }
//
//    @PutMapping("/{id}")
//    public Sensor updateSensor(@PathVariable UUID id, @RequestBody SensorCreateDto body) throws NotFoundException {
//        return sensorService.findByIdAndUpdate(id, body);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteSensor(@PathVariable UUID id) throws NotFoundException {
//
//    }
}
