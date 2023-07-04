package capstone.fe_api.dataservice.controllers;

import capstone.fe_api.dataservice.dto.RestPageImpl;
import capstone.fe_api.dataservice.dto.Sensor;
import capstone.fe_api.dataservice.dto.SensorCreateDto;
import capstone.fe_api.dataservice.dto.SensorDataResponseDto;
import capstone.fe_api.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/sensor")
@PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
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
    public Page<Sensor> getSensors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy) throws JsonProcessingException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(serviceUrl + servicePath)
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", sortBy);
        log.info("Converters: "+restTemplate.getMessageConverters().toString());

        ParameterizedTypeReference<RestPageImpl<Sensor>> responseType = new ParameterizedTypeReference<RestPageImpl<Sensor>>() { };
        ResponseEntity<RestPageImpl<Sensor>> response = restTemplate.exchange(uriBuilder.build().toUri(), HttpMethod.GET, null, responseType);


        return response.getBody();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable UUID id){
        return restTemplate.getForEntity(serviceUrl + servicePath + "/" + id, Sensor.class);
    }
    @GetMapping("/{id}/data")
    public List<SensorDataResponseDto> getSensorData(@PathVariable UUID id, @RequestParam(defaultValue = "7" ) int interval ) throws Exception {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(serviceUrl + servicePath + "/" + id  + "/data")
                .queryParam("interval", interval);
        URI uri = uriBuilder.build().toUri();
        log.info(String.valueOf(uri));
        ParameterizedTypeReference<List<SensorDataResponseDto>> responseType = new ParameterizedTypeReference<List<SensorDataResponseDto>>() {};
        ResponseEntity<List<SensorDataResponseDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
        return response.getBody();
    }
    @PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sensor> saveSensor(@RequestBody @Validated SensorCreateDto body) throws NotFoundException {
        return restTemplate.postForEntity(serviceUrl + servicePath, body, Sensor.class);
    }
    @PostAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable UUID id, @RequestBody SensorCreateDto body) {
        HttpEntity<SensorCreateDto> request = new HttpEntity<>(body);
        return restTemplate.exchange(serviceUrl + servicePath + "/" + id, HttpMethod.PUT, request, Sensor.class);
    }
    @PostAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensor(@PathVariable UUID id) throws NotFoundException {
        restTemplate.delete(serviceUrl + servicePath + "/" + id);
    }
}
