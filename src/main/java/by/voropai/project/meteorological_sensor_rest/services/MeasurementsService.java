package by.voropai.project.meteorological_sensor_rest.services;

import by.voropai.project.meteorological_sensor_rest.models.Measurement;
import by.voropai.project.meteorological_sensor_rest.models.Sensor;
import by.voropai.project.meteorological_sensor_rest.repositories.MeasurementsRepository;
import by.voropai.project.meteorological_sensor_rest.repositories.SensorsRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsRepository sensorsRepository;

    @Transactional
    public void addNewMeasurement(Measurement newMeasurement) {
        enrichMeasurement(newMeasurement);
        System.out.println(newMeasurement.getSensor());
        measurementsRepository.save(newMeasurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        Sensor sensor = measurement.getSensor();
        Optional<Sensor> sensorInDB = sensorsRepository.findByName(sensor.getName());
        sensorInDB.ifPresent(value -> sensor.setId(value.getId()));
        measurement.setMeasurementTime(LocalDateTime.now());
    }
}


