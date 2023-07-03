package capstone.fe_api.dataservice.dto;

import capstone.fe_api.utils.AlertConditions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class SensorCreateDto {

    private String name;
    private Boolean visible;
    private Double lat;
    private Double lon;
    private Double alertValue;
    private Double rangeMin;
    private Double rangeMax;
    private AlertConditions alertCondition;
    private UUID um;

    public SensorCreateDto(String name, Boolean visible, Double lat, Double lon, Double alertValue, Double rangeMin, Double rangeMax, AlertConditions alertCondition, UUID um) {
        this.name = name;
        this.visible = visible;
        this.lat = lat;
        this.lon = lon;
        this.alertValue = alertValue;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.alertCondition = alertCondition;
        this.um = um;
    }
}
