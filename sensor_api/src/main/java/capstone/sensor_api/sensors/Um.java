package capstone.sensor_api.sensors;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String type;

    @OneToMany(mappedBy = "um", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Sensor> sensors = new ArrayList<>();

}
