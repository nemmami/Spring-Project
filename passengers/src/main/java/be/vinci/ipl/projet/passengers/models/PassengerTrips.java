package be.vinci.ipl.projet.passengers.models;

import java.util.ArrayList;
import java.util.List;

public class PassengerTrips {

  private List<Trip> pending = new ArrayList<>();
  private List<Trip> accepted = new ArrayList<>();
  private List<Trip> refused = new ArrayList<>();

  public PassengerTrips() {
  }

  public void addUserPending(Trip trip) {
    pending.add(trip);
  }

  public void addUserAccepted(Trip trip) {
    accepted.add(trip);
  }

  public void addUserRefused(Trip trip) {
    refused.add(trip);
  }

}
