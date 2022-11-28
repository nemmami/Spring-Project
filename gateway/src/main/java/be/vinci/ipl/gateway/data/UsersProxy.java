package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.*;
import java.util.ArrayList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient(name = "users")
public interface UsersProxy {

  @PostMapping("users")
  void createUser( @RequestBody NewUser user);


  // pas sur si il faut utiliser @RequestBody ou @param
  @GetMapping("/users")
  User findUserEmail(@RequestParam("email") String email);

  @PutMapping("/users")
  void updatePassword(@RequestBody Credentials credentials);

  @GetMapping("/users/{id}")
  User getUserInfo(@PathVariable int id);

  @PutMapping("/users/{id}")
  void updateUserInfo(@PathVariable int id, @RequestBody User user );

  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable int id);

  @GetMapping("/users/{id}/driver")
  Iterable<Trip> getFutureDriverTrips(@PathVariable int id);

  @GetMapping("/users/{id}/passenger")
  Iterable<Trip> getFuturePassengerTrips(@PathVariable int id);

  @GetMapping("/users/{id}/notifications")
  Iterable<Notification> getUserNotification(@PathVariable int id);

  @DeleteMapping("/users/{id}/notifications")
  void deleteAllUserNotification(@PathVariable int id);

}
