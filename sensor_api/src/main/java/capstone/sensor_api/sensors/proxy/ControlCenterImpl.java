package capstone.sensor_api.sensors.proxy;

import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import capstone.sensor_api.sensors.interfaces.ControlCenter;
import capstone.sensor_api.utils.WsClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;


@Slf4j
@Component
public class ControlCenterImpl implements ControlCenter {
    private boolean alarmSent = false;
    @Autowired
    WsClient wsClient;
    @Override
    public void sendSensorData(SensorDataResponseDto data) {
        alarmSent = true;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        if (wsClient.getClientSession().isOpen()) {
            try {
                wsClient.getClientSession().sendMessage(new TextMessage(mapper.writeValueAsString(data)));
            } catch (Exception e ){
                log.error("Error: "+ e.getMessage());
            }
        } else {
            log.error("Error: WebSocket connection is not open");
        }

    }

    public boolean isAlarmSent() {
        return alarmSent;
    }
}
