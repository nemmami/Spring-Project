package be.vinci.ipl.projet.passengers;

import be.vinci.ipl.projet.passengers.models.Passenger;
import org.springframework.stereotype.Service;

@Service
public class PassengersService {

  private final PassengersRepository repository;

  public PassengersService(PassengersRepository repository) {
    this.repository = repository;
  }

  /**
   * Reads all Passengers in repository
   * @return all passengers
   */
  public Iterable<Passenger> readAll() {
    return repository.findAll();
  }

  /**
   * Creates a passenger in repository
   * @param tripId the id trip to create the passenger
   * @param userId the id user to create the passenger
   * @return The passenger created, or null if it already existed
   */
  public Passenger createOne(long tripId, long userId) {
    if (repository.existsByTripIdAndUserId(tripId, userId)) return null;

    return repository.save(Passenger.defPassenger(tripId, userId));
  }

  /**
   * Reads a passenger in repository
   * @param id ID of the passenger
   * @return The passenger, or null if it couldn't be found
   */
  public Passenger readOne(long id) {
    return repository.findById(id).orElse(null);
  }

  /**
   * Updates a passenger in repository
   * @param passenger New values of the passenger
   * @return True if the passenger was updated, or null if it couldn't be found
   */
  public boolean updateOne(Passenger passenger) {
    if (!repository.existsById(passenger.getId())) return false;
    repository.save(passenger);
    return true;
  }

  /**
   * Deletes a passenger from repository
   * @param id ID of the passenger
   * @return True if the passenger was deleted, false if it couldn't be found
   */
  public boolean deleteOne(long id) {
    if (!repository.existsById(id)) return false;
    repository.deleteById(id);
    return true;
  }

}
