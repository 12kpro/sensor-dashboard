package capstone.sensor_api.sensors.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class SensorDataResponseDto {
    private LocalDateTime time;
    private Double value;
    private UUID sensorId;

    public SensorDataResponseDto(LocalDateTime time, Double value, UUID sensorId) {
        this.time = time;
        this.value = value;
        this.sensorId = sensorId;
    }
}
