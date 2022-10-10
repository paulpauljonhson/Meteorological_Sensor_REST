package by.voropai.project.meteorological_sensor_rest.util.validation;

import by.voropai.project.meteorological_sensor_rest.dto.MeasurementDTO;
import by.voropai.project.meteorological_sensor_rest.dto.SensorDTO;
import by.voropai.project.meteorological_sensor_rest.repositories.SensorsRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor

@Component
public class MeasurementDTOValidator implements Validator {
    private final SensorsRepository sensorsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        SensorDTO sensor = measurementDTO.getSensor();
        if (!sensorsRepository.existsByName(sensor.getName())) {
            errors.rejectValue("sensor", "", "Sensor with this name" +
                    " not found!");
        }

    }
}
