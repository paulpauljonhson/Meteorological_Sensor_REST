package by.voropai.project.meteorological_sensor_rest.dto;


import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class MeasurementDTO {

    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDTO sensor;
}
