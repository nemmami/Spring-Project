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

  public static Passenger defPassenger(int tripId, int userId) {
    return new Passenger(0, tripId, userId, PassengerStatus.PENDING);
  }
}
