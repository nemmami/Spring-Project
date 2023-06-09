package be.vinci.ipl.gateway.models;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class NewTrip {
  private Position origin;
  private Position destination;
  private String departure;
  private int dirverId;
  private int availableSeating;


}
