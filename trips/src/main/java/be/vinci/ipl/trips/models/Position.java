package be.vinci.ipl.trips.models;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Position {
    private double latitude;
    private double longitude;
}
