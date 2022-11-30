package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.*;
import java.util.ArrayList;
import javax.ws.rs.QueryParam;
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

  @PostMapping("/users/{email}")
  // maybe NewUser and not user
  // not shure if should use mail or ID for user --> prob id
  void createUser(@PathVariable String email, @RequestBody User user);


  @GetMapping("/users/{email}")
  User readeUser(@PathVariable String email);

  @PutMapping("/users/{email}")
  void updateUser(@PathVariable String email,@RequestBody User user);


  @DeleteMapping("/users/{email}")
  void deleteUser(@PathVariable String email);


}
