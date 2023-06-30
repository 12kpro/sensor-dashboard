package capstone.sensor_api.sensors.proxy;

import capstone.sensor_api.sensors.Sensor;

import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.interfaces.Observer;
import capstone.sensor_api.sensors.services.SensorDataService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ControlProcess implements Observer {
    private UUID id = UUID.randomUUID();
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
        sensors.removeIf(s -> s.getId().equals(sensor.getId()));
        sensor.deAttach(this);
    }
    public List<Sensor> getSensors() {
        return sensors;
    }

    @Override
    public void update(Sensor sensor) {
            SensorData newData = new SensorData(LocalDateTime.now(),sensor.getCurrentValue(),sensor);
            sensorDataService.create(newData);
            proxy.sendMessage(sensor);

    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ControlProcess{" +
                "id=" + id +
                '}';
    }
}
