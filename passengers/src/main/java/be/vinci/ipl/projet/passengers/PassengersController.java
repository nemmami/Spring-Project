package be.vinci.ipl.projet.passengers;

import be.vinci.ipl.projet.passengers.models.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PassengersController {

  private final PassengersService service;

  public PassengersController(PassengersService service) {
    this.service = service;
  }

  @PostMapping("/passenger")
  public ResponseEntity<Passenger> createOne(@RequestBody Passenger passenger) {
    if (passenger.getId() == 0 || passenger.getTrip_id() == 0 || passenger.getUser_id() == 0
        || passenger.getStatus().equals("")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    Passenger createdPassenger = service.createOne(passenger);
    if (createdPassenger == null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
  }

  @GetMapping("/passenger/{id}")
  public Passenger readOne(@PathVariable long id) {
    Passenger review = service.readOne(id);
    if (review == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return review;
  }


}
