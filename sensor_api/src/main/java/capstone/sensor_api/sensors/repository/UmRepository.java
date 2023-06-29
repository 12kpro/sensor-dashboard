package capstone.sensor_api.sensors.repository;

import capstone.sensor_api.sensors.Um;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UmRepository extends JpaRepository<Um, UUID> {
    Optional<Um> findByTypeIgnoreCase(String type);

}
