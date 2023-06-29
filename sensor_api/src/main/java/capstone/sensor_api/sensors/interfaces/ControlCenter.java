package capstone.sensor_api.sensors.interfaces;

import capstone.sensor_api.sensors.Sensor;

public interface ControlCenter {
    void sendMessage(Sensor sensor);
}
