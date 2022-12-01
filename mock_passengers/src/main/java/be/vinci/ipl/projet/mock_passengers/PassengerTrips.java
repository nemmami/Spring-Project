package be.vinci.ipl.projet.mock_passengers;

import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassengerTrips {

  private ArrayList<Trip> pending;
  private ArrayList<Trip> accepted;
  private ArrayList<Trip> refused;
}
