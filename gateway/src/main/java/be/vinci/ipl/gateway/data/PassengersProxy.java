package be.vinci.ipl.gateway.data;


import be.vinci.ipl.gateway.models.PassengerTrips;
import be.vinci.ipl.gateway.models.Passengers;
import be.vinci.ipl.gateway.models.User;
import jakarta.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Repository
@FeignClient(name = "passengers")
public interface PassengersProxy {

  @GetMapping("/passengers")
  Iterable<Passengers> readAllPassengers();

  @GetMapping("/passengers/trips/{trip_id}")
  Iterable<Passengers> getAllPassengersFromTrip(@PathVariable int trip_id);

  @DeleteMapping("/passengers/trips/{trip_id}")
  void deleteAllPassengersFromTrip(@PathVariable int trip_id);

  @PostMapping("/passengers/trips/{trip_id}/users/{user_id}")
  void addUserToTrip(@PathVariable int trip_id, @PathVariable int user_id);

  @GetMapping("/passengers/trips/{trip_id}/users/{user_id}")
  String getPassengerStatus(@PathVariable int trip_id, @PathVariable int user_id);

  @PutMapping("/passengers/trips/{trip_id}/users/{user_id}")
  void updatePassengerStatus(@PathVariable int trip_id, @PathVariable int user_id, @QueryParam("Status") String status);

  @DeleteMapping("/passengers/trips/{trip_id}/users/{user_id}")
  void deletePassengerFromTrip(@PathVariable int trip_id, @PathVariable int user_id);

  @DeleteMapping("/passengers/users/{user_id}")
  void deletePassengersOfUser(@PathVariable int user_id);

  @GetMapping("/passengers/users/{userId}/passenger")
  PassengerTrips getAllTripsFromPassenger(@PathVariable int userId);


}
