package capstone.sensor_api.sensors.mapper;

import capstone.sensor_api.sensors.SensorData;
import capstone.sensor_api.sensors.dto.SensorDataResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SensorDataMapper {
    public SensorDataResponseDto toDTO(SensorData sensorData) {
        SensorDataResponseDto dto = new SensorDataResponseDto();
        dto.setTime(sensorData.getTime());
        dto.setValue(sensorData.getValue());
        dto.setSensorId(sensorData.getSensor().getId());
        return dto;
    }
    public List<SensorDataResponseDto> toDTOs(List<SensorData> sensorDataList) {
        return sensorDataList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
