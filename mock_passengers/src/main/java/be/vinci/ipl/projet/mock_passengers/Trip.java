package be.vinci.ipl.projet.mock_passengers;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
