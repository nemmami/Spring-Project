package be.vinci.ipl.projet.mock_passengers;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {


  private int id;
  private int tripId;
  private int userId;

  private PassengerStatus status;
}
