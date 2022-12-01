package be.vinci.ipl.projet.passengers.models;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PassengerTrips {

  private List<Trip> pending = new ArrayList<>();
  private List<Trip> accepted = new ArrayList<>();
  private List<Trip> refused = new ArrayList<>();

  public void addTripPending(Trip trip) {
    pending.add(trip);
  }

  public void addTripAccepted(Trip trip) {
    accepted.add(trip);
  }

  public void addTripRefused(Trip trip) {
    refused.add(trip);
  }

}
