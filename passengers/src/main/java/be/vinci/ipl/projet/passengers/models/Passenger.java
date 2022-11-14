package be.vinci.ipl.projet.passengers.models;

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
public class Passenger {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long trip_id;
  private long user_id;
  @Enumerated(EnumType.STRING)
  private PassengerStatus status;

}
