package by.voropai.project.meteorological_sensor_rest.repositories;

import by.voropai.project.meteorological_sensor_rest.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

    boolean existsByName(String sensorName);

    Optional<Sensor> findByName(String name);
}
