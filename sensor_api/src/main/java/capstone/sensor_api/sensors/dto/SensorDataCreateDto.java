package capstone.sensor_api.sensors.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class SensorDataCreateDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime time;
    //TODO rimuovere min e max per registrare un avaria al sensore
    @NotNull(message = "Il valore è obbligatorio")
    @Min(value = 0)
    @Max(value = 10)
    private Double value;
    @NotNull(message = "L'id del sensore è obbligatorio")
    private UUID sensor;

    public SensorDataCreateDto(LocalDateTime time, @NotNull(message = "Il valore è obbligatorio") Double value, @NotNull(message = "L' id del sensore è obbligatorio") UUID sensor) {
        this.time = time;
        this.value = value;
        this.sensor = sensor;
    }
}
