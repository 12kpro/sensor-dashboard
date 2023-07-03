package capstone.sensor_api.sensors.services;

import capstone.sensor_api.exceptions.BadRequestException;
import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.entities.Sensor;
import capstone.sensor_api.sensors.entities.Um;
import capstone.sensor_api.sensors.dto.SensorCreateDto;

import capstone.sensor_api.sensors.proxy.ControlProcess;
import capstone.sensor_api.sensors.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
public class SensorService {


    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    UmService umService;
    @Autowired
    ControlProcess process;
    public Sensor create(SensorCreateDto s) {
        sensorRepository.findByNameIgnoreCase(s.getName()).ifPresent(user -> {
            throw new BadRequestException("Sensor " + s.getName() + " già in registrato!");
        });
        Um u = umService.findById(s.getUm());
        Sensor newSensor = new Sensor();
        newSensor.setName(s.getName());
        newSensor.setVisible(s.getVisible());
        newSensor.setLat(s.getLat());
        newSensor.setLon(s.getLon());
        newSensor.setAlertValue(s.getAlertValue());
        newSensor.setAlertCondition(s.getAlertCondition());
        newSensor.setRangeMin(s.getRangeMin());
        newSensor.setRangeMax(s.getRangeMax());
        newSensor.setUm(u);
        Sensor cs = sensorRepository.save(newSensor);
        process.addSensor(cs);
        //checkSensors("CREATE ADD");
        return cs;
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
        process.rmSensor(found);
        //checkSensors("UPDATE REMOVE");
        Um u = umService.findById(s.getUm());
        found.setId(id);
        found.setName(s.getName());
        found.setVisible(s.getVisible());
        found.setLat(s.getLat());
        found.setLon(s.getLon());
        found.setAlertValue(s.getAlertValue());
        found.setAlertCondition(s.getAlertCondition());
        found.setRangeMin(s.getRangeMin());
        found.setRangeMax(s.getRangeMax());
        found.setUm(u);
        Sensor us = sensorRepository.save(found);
        process.addSensor(found);
        //checkSensors("UPDATE ADD");
        return us;
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Sensor found = this.findById(id);
        process.rmSensor(found);
        //checkSensors("DELETE");
        sensorRepository.delete(found);
    }
    private void checkSensors (String action){
        log.info("On: " + action);
        for (Sensor s : process.getSensors()) {
            log.info(s.toString());
        }
        log.info("*************************");
    }
}
