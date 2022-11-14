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
   * Reads a passenger in repository
   * @param id ID of the passenger
   * @return The passenger, or null if it couldn't be found
   */
  public Passenger readOne(long id) {
    return repository.findById(id).orElse(null);
  }



}
