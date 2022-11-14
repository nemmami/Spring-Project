package be.vinci.ipl.trips.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoIdTrip {
    @Embedded
    private Position origin;
    @Embedded
    private Position destination;
    private Date departureDate;
    private int driverId;
    private int availableSeatigng;

    public Trip toTrip() {
        return new Trip(0, origin, destination, departureDate, driverId, availableSeatigng);
    }
}
