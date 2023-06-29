package capstone.sensor_api.sensors.services;

import capstone.sensor_api.exceptions.BadRequestException;
import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.Um;
import capstone.sensor_api.sensors.dto.SensorCreateDto;
import capstone.sensor_api.sensors.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SensorService {
    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    UmService umService;
    public Sensor create(SensorCreateDto s) {
        sensorRepository.findByNameIgnoreCase(s.getName()).ifPresent(user -> {
            throw new BadRequestException("Sensor " + s + " già in registrato!");
        });
        Um u = umService.findById(s.getUm());
        Sensor newSensor = new Sensor();
        newSensor.setName(s.getName());
        newSensor.setLat(s.getLat());
        newSensor.setLon(s.getLon());
        newSensor.setAlarmValue(s.getAlarmValue());
        newSensor.setUm(u);
        return sensorRepository.save(newSensor);
    }
    public Page<Sensor> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return sensorRepository.findAll(pageable);
    }
    public Sensor findById(UUID id) throws NotFoundException {
        return sensorRepository.findById(id).orElseThrow(() -> new NotFoundException("Sensor con Id:" + id + "non trovato!!"));
    }
    public Sensor findByIdAndUpdate(UUID id, SensorCreateDto s) throws NotFoundException {
        Sensor found = this.findById(id);
        Um u = umService.findById(s.getUm());
        found.setId(id);
        found.setName(s.getName());
        found.setLat(s.getLat());
        found.setLon(s.getLon());
        found.setAlarmValue(s.getAlarmValue());
        found.setUm(u);
        return sensorRepository.save(found);
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Sensor found = this.findById(id);
        sensorRepository.delete(found);
    }
}
