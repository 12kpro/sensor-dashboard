package capstone.sensor_api.sensors.proxy;

import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.interfaces.ControlCenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ControlCenterImpl implements ControlCenter {
    private boolean alarmSent = false;
    @Override
    public void sendMessage(Sensor sensor) {
        alarmSent = true;
        //TODO implementare la request verso il frontend
        // log solo per test
        String url = "http://host/alarm?idsonda=" + sensor.getId() +
                "&lat=" + sensor.getLat() +
                "&lon=" + sensor.getLon() +
                "&smokelevel=" + sensor.getCurrentValue();
        log.error(url);
    }

    public boolean isAlarmSent() {
        return alarmSent;
    }
}
