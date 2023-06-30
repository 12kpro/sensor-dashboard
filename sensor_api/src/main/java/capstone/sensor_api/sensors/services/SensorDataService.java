package capstone.sensor_api.sensors.services;

import capstone.sensor_api.exceptions.NotFoundException;
import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.repository.SensorDataRepository;
import capstone.sensor_api.sensors.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
public class SensorDataService {
    @Autowired
    SensorDataRepository sensorDataRepository;
    public SensorData create(SensorData sd) {
        return sensorDataRepository.save(sd);
    }

    public List<SensorData> findBySensor_IddAndTimeGreaterThan(UUID id, LocalDateTime data) {
        return sensorDataRepository.findBySensor_IdAndTimeGreaterThan(id, data);
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
