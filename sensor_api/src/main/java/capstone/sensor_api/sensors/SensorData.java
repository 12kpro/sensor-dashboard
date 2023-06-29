package capstone.sensor_api.sensors;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sensors_data")
@Getter
@Setter
@NoArgsConstructor
public class SensorData {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime time;
    @NotNull
    @Column(columnDefinition = "numeric(10,2)")
    private Double value;
    @NotNull
    @ManyToOne
    private Sensor sensor;

    public SensorData(@NotNull LocalDateTime time, @NotNull Double value, @NotNull Sensor sensor) {
        this.time = time;
        this.value = value;
        this.sensor = sensor;
    }
}
