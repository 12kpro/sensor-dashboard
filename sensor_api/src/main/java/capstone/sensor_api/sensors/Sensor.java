package capstone.sensor_api.sensors;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "sensors")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Boolean visible;
    private Long lat;
    private Long lon;
    @Column(columnDefinition = "numeric(10,2)")
    private Double alarmValue;
    @ManyToOne
    private Um um;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<SensorData> sensorDatas = new ArrayList<>();

}
