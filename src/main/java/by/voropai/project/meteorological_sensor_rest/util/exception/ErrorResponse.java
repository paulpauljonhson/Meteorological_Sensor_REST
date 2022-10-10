package by.voropai.project.meteorological_sensor_rest.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public abstract class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
}
