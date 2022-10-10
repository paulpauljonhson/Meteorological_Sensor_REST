package by.voropai.project.meteorological_sensor_rest.repositories;

import by.voropai.project.meteorological_sensor_rest.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
