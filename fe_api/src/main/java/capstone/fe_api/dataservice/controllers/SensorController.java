package capstone.fe_api.dataservice.controllers;

import capstone.fe_api.dataservice.dto.Sensor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/sensor")
//@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
public class SensorController {
    private final RestTemplate restTemplate;

    public SensorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${service.url}")
    private String serviceUrl;
    private String servicePath = "/sensor";
    @GetMapping("")
    public Page<Sensor> getSensors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(serviceUrl + servicePath)
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", sortBy);

        ResponseEntity<Page<Sensor>> response = restTemplate.exchange(
                uriBuilder.build().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Sensor>>() {}
        );
        //restTemplate.getForEntity(serviceUrl + servicePath, Page.class)
        return response.getBody();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable UUID id){
        return restTemplate.getForEntity(serviceUrl + servicePath + id, Sensor.class);
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
