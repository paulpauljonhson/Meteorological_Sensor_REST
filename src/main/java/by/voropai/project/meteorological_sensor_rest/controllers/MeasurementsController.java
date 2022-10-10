package by.voropai.project.meteorological_sensor_rest.controllers;

import by.voropai.project.meteorological_sensor_rest.dto.MeasurementDTO;
import by.voropai.project.meteorological_sensor_rest.models.Measurement;
import by.voropai.project.meteorological_sensor_rest.services.MeasurementsService;
import by.voropai.project.meteorological_sensor_rest.util.exception.measurement.MeasurementErrorResponse;
import by.voropai.project.meteorological_sensor_rest.util.exception.measurement.MeasurementNotAddedException;
import by.voropai.project.meteorological_sensor_rest.util.validation.MeasurementDTOValidator;
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
@RequestMapping("/measurements")
public class MeasurementsController {
    MeasurementsService measurementsService;
    ModelMapper modelMapper;
    MeasurementDTOValidator validator;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        validator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(error -> errorMessage
                    .append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";"));
            throw new MeasurementNotAddedException(errorMessage.toString());
        }
        System.out.println(measurementDTO.getSensor());
        measurementsService.addNewMeasurement(ConvertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement ConvertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO ConvertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotAddedException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
