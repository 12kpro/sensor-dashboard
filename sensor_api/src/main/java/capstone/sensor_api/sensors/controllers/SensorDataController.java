package capstone.sensor_api.sensors.controllers;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.entities.SensorData;
import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import capstone.sensor_api.sensors.mapper.SensorDataMapper;
import capstone.sensor_api.sensors.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
//TODO da rimuovere???
@RestController
@RequestMapping("/sensordata")
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;
    @Autowired
    private SensorDataMapper sensorDataMapper;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensorData(@PathVariable UUID id) throws NotFoundException {
        sensorDataService.findBySensorIdAndDelete(id);
    }
}
