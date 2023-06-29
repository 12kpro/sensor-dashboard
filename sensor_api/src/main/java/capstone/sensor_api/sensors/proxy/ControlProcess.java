package capstone.sensor_api.sensors.proxy;

import capstone.sensor_api.sensors.Sensor;

import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.dto.SensorDataCreateDto;
import capstone.sensor_api.sensors.interfaces.Observer;
import capstone.sensor_api.sensors.services.SensorDataService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ControlProcess implements Observer {
    private List<Sensor> sensors = new ArrayList<>();
    private ControlCenterProxy proxy;
    @Autowired
    SensorDataService sensorDataService;
    public ControlProcess(ControlCenterProxy proxy) {
        this.proxy = proxy;
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
        sensor.attach(this);
    }
    public void rmSensor(Sensor sensor) {
        sensors.remove(sensor);
        sensor.deAttach(this);
    }
    public List<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public void update(Sensor sensor) {
        log.info("Update sensor");
        if (sensorDataService == null) {
            log.error("sensorDataService is null");
        } else {
            SensorData newData = new SensorData(LocalDateTime.now(),sensor.getCurrentValue(),sensor);
            sensorDataService.create(newData);
            proxy.sendMessage(sensor);
        }
//        SensorDataCreateDto newData = new SensorDataCreateDto(LocalDateTime.now(),sensor.getCurrentValue(),sensor.getId());
//        sensorDataService.create(newData);
//        proxy.sendMessage(sensor);
    }
}
