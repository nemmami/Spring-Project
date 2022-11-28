package be.vinci.ipl.gateway;


import be.vinci.ipl.gateway.models.Credentials;
import be.vinci.ipl.gateway.models.NewUser;
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

  @PostMapping("/user")
  ResponseEntity<Void> createUser(@RequestBody NewUser newUser){
    service.createUser(newUser);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
