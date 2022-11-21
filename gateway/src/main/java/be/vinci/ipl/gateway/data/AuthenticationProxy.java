package be.vinci.ipl.gateway.data;

import org.apache.http.auth.Credentials;
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

  @PostMapping("/authentication/{pseudo}")
  void createCredentials(@PathVariable String email, @RequestBody Credentials credentials);

  @PutMapping("/authentication/{pseudo}")
  void updateCredentials(@PathVariable String email, @RequestBody Credentials credentials);

  @DeleteMapping("/authentication/{pseudo}")
  void deleteCredentials(@PathVariable String pseudo);

}