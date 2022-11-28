package be.vinci.ipl.calculated_distance.models;

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