package capstone.fe_api.dataservice.dto;

import capstone.fe_api.utils.AlertConditions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class Sensor {

    private UUID id;

    private String name;
    private Boolean visible;

    private Double lat;

    private Double lon;

    private Double alertValue;

    private Double rangeMin;

    private Double rangeMax;

    private AlertConditions alertCondition;
    private Um um;

    public Sensor(UUID id, String name, Boolean visible, Double lat, Double lon, Double alertValue, Double rangeMin, Double rangeMax, AlertConditions alertCondition, Um um) {
        this.id = id;
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
