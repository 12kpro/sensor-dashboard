package capstone.sensor_api.sensors.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmCreateDto {
    @NotNull(message = "Il Type è obbligatorio")
    @Size(min = 3, max = 30, message = "Type min 3 caratteri, massimo 30")
    private String type;

    public UmCreateDto(@NotNull(message = "Il Type è obbligatorio") String type) {
        this.type = type;
    }
}
