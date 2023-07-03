package capstone.sensor_api.sensors.interfaces;

import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import capstone.sensor_api.sensors.entities.SensorData;

public interface ControlCenter {
    void sendSensorData(SensorDataResponseDto data);
}
