package capstone.fe_api.dataservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class Um {
    private UUID id;

    private String unit;

    private String description;

    public Um(UUID id, String unit, String description) {
        this.id = id;
        this.unit = unit;
        this.description = description;
    }
}
