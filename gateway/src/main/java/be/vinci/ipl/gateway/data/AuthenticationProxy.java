package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient(name = "authentication")
public interface AuthenticationProxy {

  @PostMapping("/authentication/connect")
  String connect(@RequestBody Credentials credentials);

  @PostMapping("/authentication/verify")
  String verify(@RequestBody String token);

  @PostMapping("/authentication/{email}")
  void createCredentials(@PathVariable String email, @RequestBody Credentials credentials);

  @PutMapping("/authentication/{email}")
  void updateCredentials(@PathVariable String email, @RequestBody Credentials credentials);

  @DeleteMapping("/authentication/{email}")
  void deletCredentials(@PathVariable String email);



}