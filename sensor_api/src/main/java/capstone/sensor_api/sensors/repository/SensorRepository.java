package capstone.sensor_api.sensors.repository;

import capstone.sensor_api.sensors.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    Optional<Sensor> findByNameIgnoreCase(String name);



}
