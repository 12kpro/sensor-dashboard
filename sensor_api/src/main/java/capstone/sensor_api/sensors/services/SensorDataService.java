package capstone.sensor_api.sensors.services;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.entities.SensorData;
import capstone.sensor_api.sensors.repository.SensorDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class SensorDataService {
    @Autowired
    SensorDataRepository sensorDataRepository;
    public SensorData create(SensorData sd) {
        return sensorDataRepository.save(sd);
    }

    public List<SensorData> findBySensor_IdAndTimeLessThanAndTimeGreaterThanEqual(UUID id, LocalDateTime timeStart, LocalDateTime timeEnd) {
        return sensorDataRepository.findBySensor_IdAndTimeLessThanAndTimeGreaterThanEqual(id, timeEnd,timeStart);
    }
    public List<SensorData> findBySensor_Id(UUID id) {
        return sensorDataRepository.findBySensor_Id(id);
    }
    public void findBySensorIdAndDelete(UUID id) throws NotFoundException {
        List<SensorData> sensorData = this.findBySensor_Id(id);
        for (SensorData s : sensorData) {
            sensorDataRepository.delete(s);
        }
    }
}
