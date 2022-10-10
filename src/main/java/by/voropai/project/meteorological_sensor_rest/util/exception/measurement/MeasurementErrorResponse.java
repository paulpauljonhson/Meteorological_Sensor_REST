package by.voropai.project.meteorological_sensor_rest.util.exception.measurement;

import by.voropai.project.meteorological_sensor_rest.util.exception.ErrorResponse;

import java.time.LocalDateTime;

public class MeasurementErrorResponse extends ErrorResponse {
    public MeasurementErrorResponse(String message, LocalDateTime timestamp) {
        super(message, timestamp);
    }
}
