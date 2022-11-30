package be.vinci.ipl.calculated_distance.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    @AttributeOverrides ({
            @AttributeOverride(name = "latitude", column = @Column(name = "origin_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "origin_logitude"))
    })
    private Position origin;

    @Embedded
    @AttributeOverrides ({
            @AttributeOverride(name = "latitude", column = @Column(name = "destination_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "destination_logitude"))
    })
    private Position destination;

    private LocalDate departureDate;
    private int driverId;
    private int availableSeatigng;
}