package be.vinci.ipl.projet.passengers;

import be.vinci.ipl.projet.passengers.models.Passenger;
import be.vinci.ipl.projet.passengers.models.PassengerStatus;
import be.vinci.ipl.projet.passengers.models.PassengerUsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PassengersController {

  private final PassengersService service;

  public PassengersController(PassengersService service) {
    this.service = service;
  }


  @GetMapping("/passengers")
  public Iterable<Passenger> readAll() {
    return service.readAll();
  }

  @PostMapping("/passengers/trips/{tripId}/users/{userId}")
  public ResponseEntity<Passenger> createOne(@PathVariable long tripId, @PathVariable long userId) {
    if (tripId == 0 || userId == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Passenger createdPassenger = service.createOne(tripId, userId);
    if (createdPassenger == null) {
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
  }

  @GetMapping("/passengers/trips/{tripId}/users/{userId}")
  public PassengerStatus readOne(@PathVariable long tripId, @PathVariable long userId) {
    Passenger review = service.readOne(tripId, userId);
    if (review == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return review.getStatus();
  }

  @PutMapping("/passengers/trips/{tripId}/users/{userId}")
  public void updateOne(@PathVariable long tripId, @PathVariable long userId,
      @RequestParam(name = "status") String status) {
    if (!status.equals("accepted") && !status.equals("refused")) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    boolean found = service.updateOne(tripId, userId, status);
    if (!found) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/passengers/trips/{tripId}/users/{userId}")
  public void deleteOne(@PathVariable long tripId, @PathVariable long userId) {
    boolean found = service.deleteOne(tripId, userId);
    if (!found) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/passengers/trips/{tripId}")
  public PassengerUsers readFromTrip(@PathVariable long tripId) {
    return service.readFromTrip(tripId);
  }

  @DeleteMapping("/passengers/trips/{tripId}")
  public void deleteFromTrips(@PathVariable long tripId) {
    service.deleteFromTrips(tripId);
  }


  @DeleteMapping("/passengers/users/{userId}")
  public void deleteFromUsers(@PathVariable long userId) {
    service.deleteFromUsers(userId);
  }


}
