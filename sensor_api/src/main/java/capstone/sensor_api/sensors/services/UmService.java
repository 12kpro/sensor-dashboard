package capstone.sensor_api.sensors.services;

import capstone.sensor_api.exceptions.BadRequestException;
import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.Um;
import capstone.sensor_api.sensors.dto.SensorCreateDto;
import capstone.sensor_api.sensors.dto.UmCreateDto;
import capstone.sensor_api.sensors.repository.UmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UmService {
    @Autowired
    UmRepository umRepository;

    public Um create(UmCreateDto u) {
        umRepository.findByUnitIgnoreCase(u.getUnit()).ifPresent(user -> {
            throw new BadRequestException("Um " + u.getUnit() + " già in uso!");
        });

        Um newUm = new Um();
        newUm.setUnit(u.getUnit());
        newUm.setDescription(u.getDescription());
        return umRepository.save(newUm);
    }
    public Page<Um> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return umRepository.findAll(pageable);
    }
    public Um findById(UUID id) throws NotFoundException {
        return umRepository.findById(id).orElseThrow(() -> new NotFoundException("Um con Id:" + id + "non trovato!!"));
    }
    public Um findByNomeIgnoreCase(String s) throws NotFoundException {
        return umRepository.findByUnitIgnoreCase(s).orElseThrow(() -> new NotFoundException("Um con nome:" + s + "non trovato!!"));
    }
    public Um findByIdAndUpdate(UUID id, UmCreateDto u) throws NotFoundException {
        Um found = this.findById(id);
        found.setId(id);
        found.setUnit(u.getUnit());
        found.setDescription(u.getDescription());
        return umRepository.save(found);
    }
    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Um found = this.findById(id);
        umRepository.delete(found);
    }
}
