package be.vinci.ipl.gateway.data;


import be.vinci.ipl.gateway.models.User;
import javax.ws.rs.QueryParam;
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

  @GetMapping("/passengers/pending/trip/{trip_id}")
  Iterable<User> getPendingUsers(@PathVariable int trip_id);

  @GetMapping("/passengers/accepted/trip/{trip_id}")
  Iterable<User> getAcceptedUsers(@PathVariable int trip_id);

  @GetMapping("/passengers/refused/trip/{trip_id}")
  Iterable<User> getRefusedUsers(@PathVariable int trip_id);

  @GetMapping("/passengers/trip/{trip_id}")
  Iterable<User> getAllPassengersFromTrip(@PathVariable int trip_id);

  @PostMapping("/passengers/trips/{trip_id}/user/{user_id}")
  void addUserToTrip(@PathVariable int trip_id, @PathVariable int user_id);

  @GetMapping("/passengers/trips/{trip_id}/user/{user_id}")
  String getPassengerStatus(@PathVariable int trip_id, @PathVariable int user_id);

  @PutMapping("/passengers/trips/{trip_id}/user/{user_id}")
  void updatePassengerStatus(@PathVariable int trip_id, @PathVariable int user_id, @QueryParam("Status") String status);

  @DeleteMapping("/passengers/trips/{trip_id}/user/{user_id}")
  void deletePassengerFromTrip(@PathVariable int trip_id, @PathVariable int user_id);

}
