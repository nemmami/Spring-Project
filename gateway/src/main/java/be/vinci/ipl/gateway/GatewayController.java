package be.vinci.ipl.gateway2;


import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewTrip;
import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.Notification;
import be.vinci.ipl.gateway.models.PassengerTrips;
import be.vinci.ipl.gateway.models.Passengers;
import be.vinci.ipl.gateway.models.Trip;
import be.vinci.ipl.gateway.models.User;
import javax.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost")
@RestController
public class GatewayController {

  public final GatewayService service;

  public GatewayController(GatewayService service) {
    this.service = service;
  }

  // authproxy
  @PostMapping("/auth")
  String  connect(@RequestBody Credentials credentials){
    return service.connect(credentials);
  }

  //usersProxy
  @PostMapping("/users")
  User createUser(@RequestBody NewUser newUser){
    return  service.createUser(newUser);
  }

  @GetMapping("/users")
  User findUserByMail(@RequestParam("email") String email){
    return service.findUserByMail(email);
  }

  @PutMapping("/users")
  ResponseEntity<Void> updatePassword(@RequestBody Credentials credentials, @RequestHeader("Authorization") String token){
    String userMail = service.verify(token);
    if (!userMail.equals(credentials.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    service.updatePassword(credentials);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }


  @GetMapping("/users/{id}")
  User getUserInfo(@PathVariable int id, @RequestHeader("Authorization") String token ){
    //verification
    User user = service.getUserInfo(id);
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    return service.getUserInfo(id);
  }

  @PutMapping("/users/{id}")
  ResponseEntity<Void> updateUserInfo(@PathVariable int id, @RequestBody User user, @RequestHeader("Authorization") String token ){
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    service.updateUserInfo(id, user);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }


  @DeleteMapping("/users/{id}")
  ResponseEntity<Void> deleteUser(@PathVariable int id,  @RequestHeader("Authorization") String token){
    //verification
    User user = service.getUserInfo(id);
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    service.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping("/users/{id}/driver")
  Iterable<Trip> getFutureDriverTrips(@PathVariable int id, @RequestHeader("Authorization") String token ){
    User user = service.getUserInfo(id);
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    return service.getFutureDriverTrips(id);
  }

  @GetMapping("/users/{id}/passenger")
  PassengerTrips getFuturePassengerTrips(@PathVariable int id, @RequestHeader("Authorization") String token ){
    User user = service.getUserInfo(id);
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    return  service.getFuturePassengerTrips(id);
  }

  @GetMapping("/users/{id}/notifications")
  Iterable<Notification> getUserNotification(@PathVariable int id, @RequestHeader("Authorization") String token ){
    User user = service.getUserInfo(id);
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    return service.getUserNotification(id);
  }

  @DeleteMapping("/users/{id}/notifications")
  ResponseEntity<Void> deleteAllUserNotification(@PathVariable int id,  @RequestHeader("Authorization") String token){

    User user = service.getUserInfo(id);
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    service.deleteAllUserNotification(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);

  }

  //TripsProxy

  @PostMapping("/trips")
  ResponseEntity<Void> createTrip(@RequestBody NewTrip newTrip,@RequestHeader("Authorization") String token){
    // get right user
    int driverID=  newTrip.getDirverId();
    User user = service.getUserInfo(driverID);
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    service.createTrip(newTrip);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }


  @GetMapping("/trips")
  Iterable<Trip> getListTrips(@QueryParam("departure_date") String departureDate,
      @QueryParam("originLon") String originLon, @QueryParam("originLat") String originLat, @QueryParam("destinationLat") String destinationLon ,
      @QueryParam("destinationLon") String  destinationLat) {
    return service.getListTrips(departureDate,originLon,originLat,destinationLon,destinationLat);
  }


  @GetMapping("/trips/{id}")
  Trip getTripInfo(@PathVariable int id){
      return service.getTripInfo(id);
  }

  @DeleteMapping("trips/{id}")
  ResponseEntity<Void> deleteTrip(@PathVariable int id, @RequestHeader("Authorization") String token){
    // first get driver id
    int userId = service.getTripInfo(id).getDirverId();
    // use driver id to find driver
    User user = service.getUserInfo(userId);
    // verify user
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    service.deletTrip(id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping("/trips/{id}/passengers")
  Iterable<Passengers> getPassengerList(@PathVariable int id, @RequestHeader("Authorization") String token){
    // first get driver id
    int userId = service.getTripInfo(id).getDirverId();
    // use driver id to find driver
    User user = service.getUserInfo(userId);
    // verify user
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    return service.getPassangerList(id);
  }

  @PostMapping("/trips/{trip_id}/passengers/{user_id}")
  ResponseEntity<Void> addPasengerToTrip(@PathVariable int trip_id, @PathVariable int user_id, @RequestHeader("Authorization") String token){
    User user = service.getUserInfo(user_id);
    // verify user
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    service.addPasengerToTrip(trip_id, user_id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @GetMapping("/trips/{trip_id}/passengers/{user_id}")
  String getPassengerStatus(@PathVariable int trip_id, @PathVariable int user_id,@RequestHeader("Authorization") String token){
    // use driver id to find driver
    User user = service.getUserInfo(user_id);
    // verify user
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    return service.getPassengerStatus(trip_id,user_id);
  }

  @PutMapping("/trips/{trip_id}/passengers/{user_id}")
  ResponseEntity<Void> updatePassengerStatus(@PathVariable int trip_id, @PathVariable int user_id, @RequestParam("status") String status, @RequestHeader("Authorization") String token){
    // use driver id to find driver
    User user = service.getUserInfo(user_id);
    // verify user
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
     service.updatePassengerStatus(trip_id, user_id, status);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/trips/{trip_id}/passengers/{user_id}")
  ResponseEntity<Void> removePassengerFromTrip(@PathVariable int trip_id, @PathVariable int user_id, @RequestHeader("Authorization") String token){
    // use driver id to find driver
    User user = service.getUserInfo(user_id);
    // verify user
    String userMail = service.verify(token);
    if (!userMail.equals(user.getEmail())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    service.removePassengerFromTrip(trip_id, user_id);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
}


}
