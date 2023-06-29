package capstone.sensor_api.sensors;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
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
    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime time;
    @Column(columnDefinition = "numeric(10,2)")
    private Double value;

    @ManyToOne
    private Sensor sensor;


}
