package capstone.fe_api.dataservice.dto;

import capstone.fe_api.utils.AlertConditions;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class SensorCreateDto {

    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    private String name;
    @NotNull(message = "La visiblilità è obbligatoria")
    private Boolean visible;
    @NotNull(message = "La latitudine è obbligatoria")
    private Double lat;
    @NotNull(message = "Ia longitudine è obbligatoria")
    private Double lon;
    @NotNull(message = "La soglia di allarme è obbligatoria")
    private Double alertValue;
    @NotNull(message = "La soglia minima di funzionamento è obbligatoria")
    private Double rangeMin;
    @NotNull(message = "La soglia massima di funzionamento è obbligatoria")
    private Double rangeMax;
    @NotNull(message = "La condizione è obbligatoria")
    private AlertConditions alertCondition;
    @NotNull(message = "L' unità di misura è obbligatoria")
    private UUID um;

    public SensorCreateDto(@NotNull(message = "Il nome è obbligatorio") String name, @NotNull(message = "La visiblilità è obbligatoria") Boolean visible, @NotNull(message = "La latitudine è obbligatoria") Double lat, @NotNull(message = "Ia longitudine è obbligatoria") Double lon, @NotNull(message = "La soglia di allarme è obbligatoria") Double alertValue, @NotNull(message = "La soglia minima di funzionamento è obbligatoria") Double rangeMin, @NotNull(message = "La soglia massima di funzionamento è obbligatoria") Double rangeMax, @NotNull(message = "La condizione è obbligatoria") AlertConditions alertCondition, @NotNull(message = "L' unità di misura è obbligatoria") UUID um) {
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
