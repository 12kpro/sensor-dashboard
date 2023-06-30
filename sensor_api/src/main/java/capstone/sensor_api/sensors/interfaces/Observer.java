package capstone.sensor_api.sensors.interfaces;

import capstone.sensor_api.sensors.Sensor;

public interface Observer {
    void update(Sensor sensor);

    Object getId();
}
