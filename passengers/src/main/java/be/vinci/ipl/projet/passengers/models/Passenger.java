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
  private long id;
  private long tripId;
  private long userId;
  @Enumerated(EnumType.STRING)
  private PassengerStatus status;

  public static Passenger defPassenger(long tripId, long userId) {
    return new Passenger(0, tripId, userId, PassengerStatus.PENDING);
  }

}
