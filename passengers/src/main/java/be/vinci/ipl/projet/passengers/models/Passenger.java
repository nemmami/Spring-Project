package be.vinci.ipl.projet.passengers.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "passengers")
public class Passenger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int tripId;
  private int userId;
  @Enumerated(EnumType.STRING)
  private PassengerStatus status;

  public static Passenger defPassenger(int tripId, int userId) {
    return new Passenger(0, tripId, userId, PassengerStatus.PENDING);
  }

}
