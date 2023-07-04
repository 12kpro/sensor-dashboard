package capstone.fe_api.dataservice.controllers;

import capstone.fe_api.dataservice.dto.SensorDataResponseDto;
import capstone.fe_api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
//TODO da rimuovere???
@RestController
@RequestMapping("/sensordata")
public class SensorDataController {

    private final RestTemplate restTemplate;
    public SensorDataController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${service.url}")
    private String serviceUrl;
    private String servicePath = "/sensordata";

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensorData(@PathVariable UUID id) throws NotFoundException {
        restTemplate.delete(serviceUrl + servicePath + "/" + id);
    }
}
