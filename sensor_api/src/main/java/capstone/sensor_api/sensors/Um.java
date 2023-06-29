package capstone.sensor_api.sensors;

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
    private String type;

    @OneToMany(mappedBy = "um", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Sensor> sensors = new ArrayList<>();

    public Um(@NotNull String type) {
        this.type = type;
    }
}
