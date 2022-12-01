package be.vinci.ipl.projet.passengers.models;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Trip {
  private int id;
  private Position origin;
  private Position destination;
  private LocalDate departureDate;
  private int driverId;
  private int availableSeatigng;

}

