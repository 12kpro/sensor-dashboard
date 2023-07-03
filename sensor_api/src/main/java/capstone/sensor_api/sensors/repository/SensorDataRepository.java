package capstone.sensor_api.sensors.repository;

import capstone.sensor_api.sensors.entities.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, UUID> {
    List<SensorData> findBySensor_Id(UUID id);

    List<SensorData> findBySensor_IdAndTimeGreaterThan(UUID id, LocalDateTime time);

}
