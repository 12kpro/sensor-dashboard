package capstone.sensor_api.sensors;

import capstone.sensor_api.utils.AlertConditions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import capstone.sensor_api.sensors.interfaces.Observer;
import java.util.*;

@Entity
@Table(name = "sensors")
@Getter
@NoArgsConstructor
@Slf4j
public class Sensor {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String name;
    private Boolean visible;
    @NotNull
    private Double lat;
    @NotNull
    private Double lon;
    @NotNull
    @Column(columnDefinition = "numeric(10,2)")
    private Double alertValue;
    @NotNull
    @Column(columnDefinition = "numeric(10,2)")
    private Double rangeMin;
    @NotNull
    @Column(columnDefinition = "numeric(10,2)")
    private Double rangeMax;
    @NotNull
    private AlertConditions alertCondition;
    @ManyToOne
    private Um um;
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Collection<SensorData> sensorDatas = new ArrayList<>();

    @Transient
    @JsonIgnore
    private Double currentValue;

    @Transient
    @JsonIgnore
    private List<Observer> observers = new ArrayList<>();

    public Sensor(@NotNull String name, Boolean visible, @NotNull Double lat, @NotNull Double lon, @NotNull Double alertValue, @NotNull Double rangeMin, @NotNull Double rangeMax, @NotNull AlertConditions alertCondition, Um um) {
        this.name = name;
        this.visible = visible;
        this.lat = lat;
        this.lon = lon;
        this.alertValue = alertValue;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.alertCondition = alertCondition;
        this.um = um;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setLat(@NotNull Double lat) {
        this.lat = lat;
    }

    public void setLon(@NotNull Double lon) {
        this.lon = lon;
    }

    public void setAlertValue(@NotNull Double alertValue) {
        this.alertValue = alertValue;
    }

    public void setUm(Um um) {
        this.um = um;
    }

    public void setAlertCondition(@NotNull AlertConditions alertCondition) {
        this.alertCondition = alertCondition;
    }

    public void setRangeMin(@NotNull Double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public void setRangeMax(@NotNull Double rangeMax) {
        this.rangeMax = rangeMax;
    }

    public void setCurrentValue(Double currentValue){
        this.currentValue = currentValue;
        notifyObservers();
    }
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
    public void attach(Observer observer) {
        observers.add(observer);
    }
    public void deAttach(Observer observer) {
        observers.removeIf(o -> o.getId().equals(observer.getId()));
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentValue=" + currentValue +
                '}';
    }
}
