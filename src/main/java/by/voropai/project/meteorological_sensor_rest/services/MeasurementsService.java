package by.voropai.project.meteorological_sensor_rest.services;

import by.voropai.project.meteorological_sensor_rest.models.Measurement;
import by.voropai.project.meteorological_sensor_rest.models.Sensor;
import by.voropai.project.meteorological_sensor_rest.repositories.MeasurementsRepository;
import by.voropai.project.meteorological_sensor_rest.repositories.SensorsRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public Set<LocalDate> getAllRainyDays() {
        List<Measurement> measurements = measurementsRepository.findAll();
        List<LocalDateTime> timeWithDate = measurements
                .stream()
                .filter(Measurement::getRaining)
                .map(Measurement::getMeasurementTime)
                .toList();
        return timeWithDate.stream().map(LocalDateTime::toLocalDate).collect(Collectors.toSet());
    }
}


