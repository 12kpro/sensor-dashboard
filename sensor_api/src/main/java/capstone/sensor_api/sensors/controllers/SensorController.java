package capstone.sensor_api.sensors.controllers;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import capstone.sensor_api.sensors.entities.Sensor;
import capstone.sensor_api.sensors.dto.SensorCreateDto;
import capstone.sensor_api.sensors.entities.SensorData;
import capstone.sensor_api.sensors.mapper.SensorDataMapper;
import capstone.sensor_api.sensors.services.SensorDataService;
import capstone.sensor_api.sensors.services.SensorService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorDataService sensorDataService;

    @Autowired
    private SensorDataMapper sensorDataMapper;

    @GetMapping("")
    public Page<Sensor> getSensors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return sensorService.find(page, size, sortBy);
    }
    @GetMapping("/{id}")
    public Sensor getSensor(@PathVariable UUID id) throws NotFoundException {
        return sensorService.findById(id);
    }
    @GetMapping("/{id}/data")
    public List<SensorDataResponseDto> getSensorData(@PathVariable UUID id, @RequestParam(defaultValue = "1" ) int interval , @RequestParam(defaultValue = "#{T(java.time.LocalDateTime).now()}" ) LocalDateTime date) throws Exception {
        List<SensorData> sd = sensorDataService.findBySensor_IdAndTimeLessThanAndTimeGreaterThanEqual(id,date.minusHours(interval), date);
        return sensorDataMapper.toDTOs(sd);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor saveSensor(@RequestBody @Validated SensorCreateDto body) {
        return sensorService.create(body);
    }

    @PutMapping("/{id}")
    public Sensor updateSensor(@PathVariable UUID id, @RequestBody SensorCreateDto body) throws NotFoundException {
        return sensorService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensor(@PathVariable UUID id) throws NotFoundException {
        sensorService.findByIdAndDelete(id);
    }
}
