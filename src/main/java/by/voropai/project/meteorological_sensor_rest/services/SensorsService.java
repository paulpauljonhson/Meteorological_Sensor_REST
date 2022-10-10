package by.voropai.project.meteorological_sensor_rest.services;

import by.voropai.project.meteorological_sensor_rest.models.Sensor;
import by.voropai.project.meteorological_sensor_rest.repositories.SensorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Transactional
    public void registerNewSensor(Sensor newSensor) {
        sensorsRepository.save(newSensor);
    }
}
