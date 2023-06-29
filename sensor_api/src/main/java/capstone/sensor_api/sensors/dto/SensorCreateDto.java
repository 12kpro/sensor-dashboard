package capstone.sensor_api.sensors.dto;


import capstone.sensor_api.utils.AlertConditions;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Getter
@Setter
public class SensorCreateDto {

    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    private String name;
    private Boolean visible;
    @NotNull(message = "La latitudine è obbligatoria")
    private Long lat;
    @NotNull(message = "Ia longitudine è obbligatoria")
    private Long lon;
    @NotNull(message = "La soglia di allarme è obbligatoria")
    @Min(value = 0)
    @Max(value = 10)
    private Double alertValue;
    @NotNull(message = "La condizione è obbligatoria")
    private AlertConditions alertCondition;
    @NotNull(message = "L' unità di misura è obbligatoria")
    private UUID um;

    public SensorCreateDto(@NotNull(message = "Il nome è obbligatorio") String name, Boolean visible, @NotNull(message = "La latitudine è obbligatoria") Long lat, @NotNull(message = "Ia longitudine è obbligatoria") Long lon, @NotNull(message = "La soglia di allarme è obbligatoria") Double alertValue, @NotNull(message = "La condizione è obbligatoria") AlertConditions alertCondition, @NotNull(message = "L' unità di misura è obbligatoria") UUID um) {
        this.name = name;
        this.visible = visible;
        this.lat = lat;
        this.lon = lon;
        this.alertValue = alertValue;
        this.alertCondition = alertCondition;
        this.um = um;
    }
}