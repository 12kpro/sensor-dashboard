package capstone.sensor_api.sensors.controllers;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.dto.SensorDataCreateDto;
import capstone.sensor_api.sensors.services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
//TODO testare il controller dei sensori e rimuovere
@RestController
@RequestMapping("/sensorsdata")
public class SensorDataController {
    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping("/{id}")
    public Page<SensorData> getSensorData(@PathVariable UUID id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) throws Exception {
        return sensorDataService.findBySensor_Id(id,page, size, sortBy);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSensorData(@PathVariable UUID id) throws NotFoundException {
        sensorDataService.findByIdAndDelete(id);
    }
}
