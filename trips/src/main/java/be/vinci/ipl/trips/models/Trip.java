package be.vinci.ipl.trips.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Position origin;
    @Embedded
    private Position destination;
    private Date departureDate;
    private int driverId;
    private int availableSeatigng;
}
