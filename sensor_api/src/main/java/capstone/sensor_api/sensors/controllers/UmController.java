package capstone.sensor_api.sensors.controllers;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.entities.Um;
import capstone.sensor_api.sensors.dto.UmCreateDto;
import capstone.sensor_api.sensors.services.UmService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/um")
public class UmController {
    @Autowired
    private UmService umService;
    //per endpoint paginato
    // @GetMapping("")
    // public Page<Um> getUms(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
    //                        @RequestParam(defaultValue = "id") String sortBy) {
    //     return umService.find(page, size, sortBy);
    // }

    @GetMapping("")
    public List<Um> getUms() {
        return umService.find();
    }

    @GetMapping("/{id}")
    public Um getUm(@PathVariable UUID id) throws Exception {
        return umService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Um saveUm(@RequestBody @Validated UmCreateDto body) throws Exception {
        return umService.create(body);
    }

    @PutMapping("/{id}")
    public Um updateUm(@PathVariable UUID id, @RequestBody @Validated UmCreateDto body) throws Exception {
        return umService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUm(@PathVariable UUID id) throws NotFoundException {
        umService.findByIdAndDelete(id);
    }
}
