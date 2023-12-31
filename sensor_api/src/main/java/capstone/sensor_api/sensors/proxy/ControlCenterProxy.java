package capstone.sensor_api.sensors.proxy;

import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import capstone.sensor_api.sensors.entities.SensorData;
import capstone.sensor_api.sensors.interfaces.ControlCenter;
import org.springframework.stereotype.Component;

@Component
public class ControlCenterProxy implements ControlCenter {
    private ControlCenterImpl controlCenter;

    public ControlCenterProxy(ControlCenterImpl controlCenter) {
        this.controlCenter = controlCenter;
    }

    @Override
    public void sendSensorData(SensorDataResponseDto data) {

        controlCenter.sendSensorData(data);
    }

}
