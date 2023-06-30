package capstone.sensor_api.sensors.controllers;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import capstone.sensor_api.sensors.mapper.SensorDataMapper;
import capstone.sensor_api.sensors.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sensordata")
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;
    @Autowired
    private SensorDataMapper sensorDataMapper;
    @GetMapping("/{id}")
    public List<SensorDataResponseDto> getSensorData(@PathVariable UUID id, @RequestParam(defaultValue = "7" ) int interval ) throws Exception {
        LocalDateTime data = LocalDateTime.now();
        List<SensorData> sd = sensorDataService.findBySensor_IddAndTimeGreaterThan(id,data.minusDays(interval));
        return sensorDataMapper.toDTOs(sd);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensor(@PathVariable UUID id) throws NotFoundException {
        sensorDataService.findBySensorIdAndDelete(id);
    }
}
