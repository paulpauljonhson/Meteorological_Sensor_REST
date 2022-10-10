package by.voropai.project.meteorological_sensor_rest.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class SensorDTO {

    @NotEmpty(message = "Shouldn't be empty!")
    @Size(min = 2, max = 30, message = "Name length should be from 2 to 30 letters!")
    private String name;

}
