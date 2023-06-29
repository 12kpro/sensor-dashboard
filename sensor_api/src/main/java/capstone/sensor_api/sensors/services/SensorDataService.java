package capstone.sensor_api.sensors.services;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.repository.SensorDataRepository;
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
public class SensorDataService {
    @Autowired
    SensorDataRepository sensorDataRepository;

    public SensorData create(SensorData sd) {
        SensorData newSensorData = new SensorData();
        newSensorData.setSensor(sd.getSensor());
        newSensorData.setValue(sd.getValue());
        newSensorData.setTime(sd.getTime());

        return sensorDataRepository.save(newSensorData);
    }


    public Page<SensorData> findBySensor_Id(UUID id,int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return sensorDataRepository.findBySensor_Id(id, pageable);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        SensorData found = sensorDataRepository.findById(id).orElseThrow(() -> new NotFoundException("SensorData con Id:" + id + "non trovato!!"));
        sensorDataRepository.delete(found);
    }
//TODO non necessari

//    public SensorData findByIdAndUpdate(UUID id, Sensor s) throws NotFoundException {
//        SensorData found = this.findById(id);
//        found.setId(id);
//        found.setName(s.getName());
//        return sensorDataRepository.save(found);
//    }
//    public void findByIdAndDelete(UUID id) throws NotFoundException {
//        SensorData found = this.findById(id);
//        sensorDataRepository.delete(found);
//    }
//public Page<SensorData> find(int page, int size, String sortBy) {
//    if (size < 0)
//        size = 10;
//    if (size > 100)
//        size = 100;
//    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//
//    return sensorDataRepository.findAll(pageable);
//}

}
