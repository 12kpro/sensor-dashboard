package capstone.fe_api.dataservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmCreateDto {
    @NotNull(message = "L'unità è obbligatoria")
    @Size(min = 1, max = 15, message = "Type min 1 caratteri, massimo 15")
    private String unit;

    @NotNull(message = "La descrizione è obbligatorio")
    @Size(min = 1, max = 30, message = "Descrizione min 1 caratteri, massimo 30")
    private String description;

    public UmCreateDto(@NotNull(message = "L'unità è obbligatoria") String unit, @NotNull(message = "La descrizione è obbligatorio") String description) {
        this.unit = unit;
        this.description = description;
    }
}
