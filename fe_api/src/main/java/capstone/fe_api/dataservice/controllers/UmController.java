package capstone.fe_api.dataservice.controllers;

import capstone.fe_api.dataservice.dto.RestPageImpl;
import capstone.fe_api.dataservice.dto.Um;
import capstone.fe_api.dataservice.dto.UmCreateDto;
import capstone.fe_api.exceptions.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/um")
@PreAuthorize("hasAuthority('ADMIN')")
public class UmController {
    private final RestTemplate restTemplate;
    public UmController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${service.url}")
    private String serviceUrl;
    private String servicePath = "/um";
    @GetMapping("")
    public Page<Um> getUm(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "id") String sortBy) throws JsonProcessingException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(serviceUrl + servicePath)
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sortBy", sortBy);
        ParameterizedTypeReference<RestPageImpl<Um>> responseType = new ParameterizedTypeReference<RestPageImpl<Um>>() { };
        ResponseEntity<RestPageImpl<Um>> response = restTemplate.exchange(uriBuilder.build().toUri(), HttpMethod.GET, null, responseType);
        return response.getBody();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Um> getUm(@PathVariable UUID id){
        return restTemplate.getForEntity(serviceUrl + servicePath + "/" + id, Um.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Um> saveUm(@RequestBody @Validated UmCreateDto body) throws NotFoundException {
        return restTemplate.postForEntity(serviceUrl + servicePath, body, Um.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Um> updateUm(@PathVariable UUID id, @RequestBody UmCreateDto body) {
        HttpEntity<UmCreateDto> request = new HttpEntity<>(body);
        return restTemplate.exchange(serviceUrl + servicePath + "/" + id, HttpMethod.PUT, request, Um.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUm(@PathVariable UUID id) throws NotFoundException {
        restTemplate.delete(serviceUrl + servicePath + "/" + id);
    }
}
