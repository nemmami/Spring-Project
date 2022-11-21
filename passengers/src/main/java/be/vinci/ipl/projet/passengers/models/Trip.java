package be.vinci.ipl.projet.passengers.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Trip {
  private int id;
  private Position origin;
  private Position destination;
  private LocalDate departureDate;
  private int driverId;
  private int availableSeatigng;

}
