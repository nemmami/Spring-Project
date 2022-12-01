package be.vinci.ipl.projet.passengers;

import be.vinci.ipl.projet.passengers.data.PassengersRepository;
import be.vinci.ipl.projet.passengers.data.TripsProxy;
import be.vinci.ipl.projet.passengers.data.UsersProxy;

import be.vinci.ipl.projet.passengers.models.Passenger;
import be.vinci.ipl.projet.passengers.models.PassengerStatus;
import be.vinci.ipl.projet.passengers.models.PassengerTrips;
import be.vinci.ipl.projet.passengers.models.PassengerUsers;
import be.vinci.ipl.projet.passengers.models.Trip;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PassengersService {

  private final PassengersRepository repository;
  private final UsersProxy usersProxy;

  private final TripsProxy tripsProxy;

  public PassengersService(PassengersRepository repository, UsersProxy usersProxy,
      TripsProxy tripsProxy) {
    this.repository = repository;
    this.usersProxy = usersProxy;
    this.tripsProxy = tripsProxy;
  }

  /**
   * Reads all Passengers in repository
   *
   * @return all passengers
   */
  public Iterable<Passenger> readAll() {
    return repository.findAll();
  }

  /**
   * Creates a passenger in repository
   *
   * @param tripId the id trip to create the passenger
   * @param userId the id user to create the passenger
   * @return The passenger created, or null if it already existed
   */
  public Passenger createOne(int tripId, int userId) {
    if (repository.existsByTripIdAndUserId(tripId, userId)) {
      return null;
    }

    return repository.save(Passenger.defPassenger(tripId, userId));
  }

  /**
   * Reads a passenger in repository
   *
   * @param tripId the id trip of the passenger
   * @param userId the id user of the passenger
   * @return The passenger, or null if it couldn't be found
   */
  public Passenger readOne(int tripId, int userId) {
    return repository.findByTripIdAndUserId(tripId, userId).orElse(null);
  }

  /**
   * Updates a passenger in repository
   *
   * @param tripId the id trip of the passenger
   * @param userId the id user of the passenger
   * @param status the status that the passenger will have
   * @return True if the passenger was updated, or false if it couldn't be found or status not good
   */
  public boolean updateOne(int tripId, int userId, String status) {
    if (!repository.existsByTripIdAndUserId(tripId, userId)) {
      return false;
    }
    Passenger p = readOne(tripId, userId);
    if (status.equals("accepted")) {
      p.setStatus(PassengerStatus.ACCEPTED);
    } else {
      p.setStatus(PassengerStatus.REFUSED);
    }

    repository.save(p);
    return true;
  }

  /**
   * Deletes a passenger from repository
   *
   * @param tripId the id trip of the passenger
   * @param userId the id user of the passenger
   * @return True if the passenger was deleted, false if it couldn't be found
   */
  public boolean deleteOne(int tripId, int userId) {
    if (!repository.existsByTripIdAndUserId(tripId, userId)) {
      return false;
    }
    repository.deleteByTripIdAndUserId(tripId, userId);
    return true;
  }

  /**
   * Reads all passengers from a trip, Find the accepted, refused and pending users from a trip
   *
   * @param tripId the id trip of a passenger
   * @return The list of passengers from this trip
   */
  public PassengerUsers readFromTrip(int tripId) {
    List<Passenger> pass = (List<Passenger>) repository.findByTripId(tripId);
    System.out.println(pass.size());
    PassengerUsers listUsers = new PassengerUsers();

    for (Passenger p : pass) {
      if (p.getStatus().equals(PassengerStatus.PENDING)) {
        listUsers.addUserPending(usersProxy.readOne(p.getUserId()));
      } else if (p.getStatus().equals(PassengerStatus.ACCEPTED)) {
        listUsers.addUserAccepted(usersProxy.readOne(p.getUserId()));
      } else {
        listUsers.addUserRefused(usersProxy.readOne(p.getUserId()));
      }
    }

    return listUsers;
  }

  /**
   * Get trips where user is a passenger with a future departure date by status
   *
   * @param userId the id user of a passenger
   * @return The list of passengers from this user
   */
  public PassengerTrips readFromPassenger(int userId) {
    List<Passenger> pass = (List<Passenger>) repository.findByUserId(userId);

    PassengerTrips listTrips = new PassengerTrips();

    for (Passenger p : pass) {
      Trip t = tripsProxy.readOne(p.getTripId());

      if (t.getDepartureDate().equals(null)) {
        continue;
      }

      if (p.getStatus().equals(PassengerStatus.PENDING)) {
        listTrips.addTripPending(t);
      } else if (p.getStatus().equals(PassengerStatus.ACCEPTED)) {
        listTrips.addTripAccepted(t);
      } else {
        listTrips.addTripRefused(t);
      }
    }

    return listTrips;
  }


  /**
   * Deletes all passengers of a trip
   *
   * @param tripId the id trip of the passenger
   */
  public void deleteFromTrips(int tripId) {
    repository.deleteByTripId(tripId);
  }


  /**
   * Deletes all passengers of a user
   *
   * @param userId the id user of the passenger
   */
  public void deleteFromUsers(int userId) {
    repository.deleteByUserId(userId);
  }


}
