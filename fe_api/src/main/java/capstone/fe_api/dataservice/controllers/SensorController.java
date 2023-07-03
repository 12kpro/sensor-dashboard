package capstone.fe_api.dataservice.controllers;

import capstone.fe_api.dataservice.dto.RestPageImpl;
import capstone.fe_api.dataservice.dto.Sensor;
import capstone.fe_api.dataservice.dto.SensorCreateDto;
import capstone.fe_api.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    @Autowired
    ObjectMapper objectMapper;
    private String servicePath = "/sensor";
    @GetMapping("")
    public ResponseEntity<Page<Sensor>> getSensors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy) throws JsonProcessingException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(serviceUrl + servicePath)
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", sortBy);
        log.info("Converters: "+restTemplate.getMessageConverters().toString());

//        ResponseEntity<Page<Sensor>> response = restTemplate.exchange(
//                uriBuilder.build().toUri(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Page<Sensor>>() {}
//        );

        //Page<Sensor> sensors = objectMapper.readValue(response.getBody().toString(), new TypeReference<RestPageImpl<Sensor>>() {});

        //restTemplate.getForEntity(serviceUrl + servicePath, Page.class)
        return restTemplate.exchange(
                uriBuilder.build().toUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Sensor>>() {}
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable UUID id){
        return restTemplate.getForEntity(serviceUrl + servicePath + "/" + id, Sensor.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sensor> saveSensor(@RequestBody @Validated SensorCreateDto body) throws NotFoundException {
        return restTemplate.postForEntity(serviceUrl + servicePath, body, Sensor.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable UUID id, @RequestBody SensorCreateDto body) {
        HttpEntity<SensorCreateDto> request = new HttpEntity<>(body);
        return restTemplate.exchange(serviceUrl + servicePath + "/" + id, HttpMethod.PUT, request, Sensor.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensor(@PathVariable UUID id) throws NotFoundException {
        restTemplate.delete(serviceUrl + servicePath + "/" + id);
    }
}
