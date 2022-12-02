package be.vinci.ipl.trips.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoIdTrip {
    private Position origin;
    private Position destination;
    private LocalDate departureDate;
    private int driverId;
    private int availableSeating;
    public Trip toTrip() {
        return new Trip(0, origin, destination, departureDate, driverId, availableSeating);
    }
}
