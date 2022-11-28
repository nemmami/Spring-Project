package be.vinci.ipl.gateway;


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
}
