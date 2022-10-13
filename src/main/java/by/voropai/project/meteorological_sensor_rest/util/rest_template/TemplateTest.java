package by.voropai.project.meteorological_sensor_rest.util.rest_template;

import by.voropai.project.meteorological_sensor_rest.dto.MeasurementDTO;
import by.voropai.project.meteorological_sensor_rest.dto.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TemplateTest {
    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        /* Register new sensor in Database using RestTemplate */
        String createSensorURl = "http://localhost:8080/sensors/registration";
        HttpEntity<SensorDTO> createSensorRequest = new HttpEntity<>(new SensorDTO("NameFromRestTmpl"));
        restTemplate.postForObject(createSensorURl, createSensorRequest, SensorDTO.class);
//        ------------------------------------------------------------------------------------------

        /* Add 1000 measurements */
        String createMeasurementURL = "http://localhost:8080/measurements/add";
        for (double i = 1; i <= 1000; i++) {
            HttpEntity<MeasurementDTO> createMeasurementRequest = new HttpEntity<>(
                    new MeasurementDTO(i / 10, new Random().nextBoolean(), new SensorDTO("NameFromRestTmpl")));
            restTemplate.postForObject(createMeasurementURL, createMeasurementRequest, String.class);
        }
//       -------------------------------------------------------------------------------------------

        /* Get all measurements to -> Array -> List<MeasurementsDTO> */
        String getAllMeasurementsURL = "http://localhost:8080/measurements";
        ResponseEntity<MeasurementDTO[]> response = (restTemplate.getForEntity(getAllMeasurementsURL, MeasurementDTO[].class));
        List<MeasurementDTO> measurementDTOS = Arrays.stream(Objects.requireNonNull(response.getBody())).toList();
        measurementDTOS.forEach(System.out::println);
    }
}

