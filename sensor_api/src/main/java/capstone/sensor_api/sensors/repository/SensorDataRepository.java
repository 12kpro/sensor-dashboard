package capstone.sensor_api.sensors.repository;

import capstone.sensor_api.sensors.entities.SensorData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, UUID> {
    List<SensorData> findBySensor_Id(UUID id);

    List<SensorData> findBySensor_IdAndTimeLessThanAndTimeGreaterThanEqual(UUID id, LocalDateTime timeEnd, LocalDateTime timeStart);

    List<SensorData> findBySensor_IdAndTimeGreaterThanEqualAndTimeLessThan(UUID id, LocalDateTime timeStart, LocalDateTime timeEnd);

    List<SensorData> findBySensor_IdAndTimeBetween(UUID id, LocalDateTime timeStart, LocalDateTime timeEnd);

}
