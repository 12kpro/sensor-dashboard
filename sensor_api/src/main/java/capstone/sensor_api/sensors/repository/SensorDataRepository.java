package capstone.sensor_api.sensors.repository;

import capstone.sensor_api.sensors.SensorData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, UUID> {
    Page<SensorData> findBySensor_Id(UUID id, Pageable pageable);

}
