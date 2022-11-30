package be.vinci.ipl.gateway;


import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewUser;
import be.vinci.ipl.gateway.models.User;
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

  @PostMapping("/auth")
  String  connect(@RequestBody Credentials credentials){
    return service.connect(credentials);
  }

  @PostMapping("/users")
  ResponseEntity<Void> createUser(@RequestBody NewUser newUser){
    service.createUser(newUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
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
  User getUserInfo(@PathVariable int id){
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
  ResponseEntity<Void> deleteUser(@PathVariable int id){
    service.deleteUser(id);

    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }



}
