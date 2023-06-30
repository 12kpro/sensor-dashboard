package capstone.sensor_api.sensors.controllers;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
//TODO testare il controller dei sensori e rimuovere
@RestController
@RequestMapping("/sensorsdata")
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping("/{id}")
    public List<SensorData> getSensorData(@PathVariable UUID id, @RequestParam(defaultValue = "7" ) int interval ) throws Exception {
        LocalDateTime data = LocalDateTime.now();
        return sensorDataService.findBySensor_IddAndTimeGreaterThan(id,data.minusDays(interval));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensor(@PathVariable UUID id) throws NotFoundException {
        sensorDataService.findBySensorIdAndDelete(id);
    }
}
