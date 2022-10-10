package by.voropai.project.meteorological_sensor_rest.util.validation;

import by.voropai.project.meteorological_sensor_rest.dto.SensorDTO;
import by.voropai.project.meteorological_sensor_rest.repositories.SensorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor

@Component
public class SensorDTOValidator implements Validator {
    private final SensorsRepository sensorsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;

        if (sensorsRepository.existsByName(sensor.getName())){
            errors.rejectValue("name", "", "This sensor name is already registered!");
        }

    }
}
