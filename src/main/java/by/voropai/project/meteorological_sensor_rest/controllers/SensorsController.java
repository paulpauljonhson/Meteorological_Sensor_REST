package by.voropai.project.meteorological_sensor_rest.controllers;

import by.voropai.project.meteorological_sensor_rest.dto.SensorDTO;
import by.voropai.project.meteorological_sensor_rest.models.Sensor;
import by.voropai.project.meteorological_sensor_rest.services.SensorsService;
import by.voropai.project.meteorological_sensor_rest.util.exception.sensor.SensorErrorResponse;
import by.voropai.project.meteorological_sensor_rest.util.exception.sensor.SensorNotRegisteredException;
import by.voropai.project.meteorological_sensor_rest.util.validation.SensorDTOValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorDTOValidator sensorDTOValidator;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                     BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(error -> errorMessage
                    .append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";"));
            throw new SensorNotRegisteredException(errorMessage.toString());
        }

        sensorsService.registerNewSensor(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensor(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotRegisteredException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
