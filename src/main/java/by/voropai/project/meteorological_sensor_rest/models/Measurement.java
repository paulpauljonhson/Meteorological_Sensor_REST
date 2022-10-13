package by.voropai.project.meteorological_sensor_rest.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "value")
    @NotNull
    @Min(-100) @Max(100)
    private Double value;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @Column(name = "measurement_time")
    private LocalDateTime measurementTime;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @Cascade(CascadeType.DELETE)
    private Sensor sensor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
