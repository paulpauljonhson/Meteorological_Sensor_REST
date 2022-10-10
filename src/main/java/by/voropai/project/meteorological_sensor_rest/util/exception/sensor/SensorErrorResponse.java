package by.voropai.project.meteorological_sensor_rest.util.exception.sensor;

import by.voropai.project.meteorological_sensor_rest.util.exception.ErrorResponse;

import java.time.LocalDateTime;

public class SensorErrorResponse extends ErrorResponse {
    public SensorErrorResponse(String message, LocalDateTime timestamp) {
        super(message, timestamp);
    }
}
