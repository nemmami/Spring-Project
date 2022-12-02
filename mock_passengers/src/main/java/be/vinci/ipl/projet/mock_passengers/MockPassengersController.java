package be.vinci.ipl.projet.mock_passengers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

public class MockPassengersController {


  @GetMapping("/passengers")
  public Iterable<Passenger> readAll() {
    List<Passenger> passengers = new ArrayList<>();
    passengers.add(new Passenger(0, 1, 1, PassengerStatus.PENDING));
    passengers.add(new Passenger(0, 1, 2, PassengerStatus.PENDING));
    return passengers;
  }

  @PostMapping("/passengers/trips/{tripId}/users/{userId}")
  public ResponseEntity<Passenger> createOne(@PathVariable int tripId, @PathVariable int userId) {




    return new ResponseEntity<>(Passenger.defPassenger(tripId, userId), HttpStatus.CREATED);
  }

  @GetMapping("/passengers/trips/{tripId}/users/{userId}")
  public PassengerStatus readOne(@PathVariable int tripId, @PathVariable int userId) {

    return Passenger.defPassenger(tripId, userId).getStatus();
  }

  @PutMapping("/passengers/trips/{tripId}/users/{userId}")
  public void updateOne(@PathVariable int tripId, @PathVariable int userId,
      @RequestParam(name = "status") String status) {
  }

  @DeleteMapping("/passengers/trips/{tripId}/users/{userId}")
  public void deleteOne(@PathVariable int tripId, @PathVariable int userId) {

  }

  @GetMapping("/passengers/trips/{tripId}")
  public PassengerUsers readFromTrip(@PathVariable int tripId) {
    PassengerUsers pusers = new PassengerUsers();
    pusers.getAccepted().add(new User(1, "1@gmail.com", "t1", "t1"));
    pusers.getRefused().add(new User(2, "2@gmail.com", "t2", "t2"));
    pusers.getPending().add(new User(3, "3@gmail.com", "t3", "t3"));

    return pusers;
  }

  @GetMapping("/passengers/users/{userId}/passenger")
  public PassengerTrips readFromPassenger(@PathVariable int userId) {
    PassengerTrips ptrips = new PassengerTrips();
    Position pt = new Position(12.3, 12.3);
    ptrips.getAccepted().add(new Trip(1, pt,pt, LocalDate.now(), 1, 1));
    ptrips.getRefused().add(new Trip(2, pt,pt, LocalDate.now(), 2, 1));
    ptrips.getPending().add(new Trip(3, pt,pt, LocalDate.now(), 2, 1));

    return ptrips;
  }

  @DeleteMapping("/passengers/trips/{tripId}")
  public void deleteFromTrips(@PathVariable int tripId) {

  }


  @DeleteMapping("/passengers/users/{userId}")
  public void deleteFromUsers(@PathVariable int userId) {

  }

}
