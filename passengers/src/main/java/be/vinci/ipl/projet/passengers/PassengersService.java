package be.vinci.ipl.projet.passengers;

import be.vinci.ipl.projet.passengers.data.PassengersRepository;
import be.vinci.ipl.projet.passengers.data.UsersProxy;

import be.vinci.ipl.projet.passengers.models.Passenger;
import be.vinci.ipl.projet.passengers.models.PassengerStatus;
import be.vinci.ipl.projet.passengers.models.PassengerUsers;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PassengersService {

  private final PassengersRepository repository;
  private final UsersProxy usersProxy;

  public PassengersService(PassengersRepository repository, UsersProxy usersProxy) {
    this.repository = repository;
    this.usersProxy = usersProxy;
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
  public Passenger createOne(long tripId, long userId) {
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
  public Passenger readOne(long tripId, long userId) {
    return repository.findByTripIdAndUserId(tripId, userId).orElse(null);
  }

  /**
   * Updates a passenger in repository
   *
   * @param passenger New values of the passenger
   * @return True if the passenger was updated, or null if it couldn't be found
   */
  public boolean updateOne(Passenger passenger) {
    if (!repository.existsById(passenger.getId())) {
      return false;
    }
    repository.save(passenger);
    return true;
  }

  /**
   * Deletes a passenger from repository
   *
   * @param tripId the id trip of the passenger
   * @param userId the id user of the passenger
   * @return True if the passenger was deleted, false if it couldn't be found
   */
  public boolean deleteOne(long tripId, long userId) {
    if (!repository.existsByTripIdAndUserId(tripId, userId)) {
      return false;
    }
    repository.deleteByTripIdAndUserId(tripId, userId);
    return true;
  }

  /**
   * Reads all passengers from a trip
   *
   * @param tripId Pseudo of the user
   * @return The list of reviews from this user
   */
  public PassengerUsers readFromTrip(long tripId) {
    List<Passenger> pass = (List<Passenger>) repository.findByTripId(tripId);
    System.out.println(pass.size());
    PassengerUsers listUsers = new PassengerUsers();

    for (Passenger p : pass) {
      if (p.getStatus().equals(PassengerStatus.PENDING)) {
        listUsers.addUserPending(p.getUserId());
      } else if (p.getStatus().equals(PassengerStatus.ACCEPTED)) {
        listUsers.addUserAccepted(p.getUserId());
      } else {
        listUsers.addUserRefused(p.getUserId());
      }
    }

    //    for (Passenger p : pass) {
//      if (p.getStatus().equals(PassengerStatus.PENDING)) {
//        listUsers.addUserPending(usersProxy.readOne(p.getUserId()));
//      } else if (p.getStatus().equals(PassengerStatus.ACCEPTED)) {
//        listUsers.addUserAccepted(usersProxy.readOne(p.getUserId()));
//      } else {
//        listUsers.addUserRefused(usersProxy.readOne(p.getUserId()));
//      }
//    }

    return listUsers;
  }

  /**
   * Reads all reviews of a video
   * @param tripId Hash of the video
   * @return The list of reviews of this video
   */
  public Iterable<Passenger> readFromTrips(long tripId) {
    return repository.findByTripId(tripId);
  }

  /**
   * Deletes all reviews of a video
   * @param tripId Hash of the video
   */
  public void deleteFromTrips(long tripId) {
    repository.deleteByTripId(tripId);
  }



}
