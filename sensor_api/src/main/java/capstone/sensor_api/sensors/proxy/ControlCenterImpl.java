package capstone.sensor_api.sensors.proxy;

import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import capstone.sensor_api.sensors.interfaces.ControlCenter;
import capstone.sensor_api.utils.WsClient;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        //TODO verificare e/o correggere il payload {"time":[2023,7,3,1,51,41,248857700],"value":20.07024906284523,"sensorId":"86407637-0d03-4594-9b5c-fa27ce357a69"}
        // o qui o nella get del controller sensor data
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
