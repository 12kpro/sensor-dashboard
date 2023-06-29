package capstone.sensor_api.sensors.controllers;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.Um;
import capstone.sensor_api.sensors.dto.SensorCreateDto;
import capstone.sensor_api.sensors.dto.UmCreateDto;
import capstone.sensor_api.sensors.services.SensorService;
import capstone.sensor_api.sensors.services.UmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/um")
public class UmController {
    @Autowired
    private UmService umService;

    @GetMapping("")
    public Page<Um> getUms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "id") String sortBy) {
        return umService.find(page, size, sortBy);
    }
    @GetMapping("/{id}")
    public Um getUm(@PathVariable UUID id) throws Exception {
        return umService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Um saveUm(@RequestBody UmCreateDto body) {
        return umService.create(body);
    }

    @PutMapping("/{id}")
    public Um updateUm(@PathVariable UUID id, @RequestBody UmCreateDto body) throws Exception {
        return umService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUm(@PathVariable UUID id) throws NotFoundException {
        umService.findByIdAndDelete(id);
    }
}
