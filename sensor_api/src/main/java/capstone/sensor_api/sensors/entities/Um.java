package capstone.sensor_api.sensors.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "um")
@Getter
@Setter
@NoArgsConstructor
public class Um {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String unit;
    @NotNull
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "um", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Sensor> sensors = new ArrayList<>();

    public Um(@NotNull String unit, @NotNull String description) {
        this.unit = unit;
        this.description = description;
    }
}
