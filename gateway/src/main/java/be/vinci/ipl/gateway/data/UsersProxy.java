package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(name = "users")
public interface UsersProxy {

  @PostMapping("/users/")
  // maybe NewUser and not user
  // not shure if should use mail or ID for user --> prob id
  User createUser( @RequestBody NewUser user);

  @GetMapping("/users/{email}")
  User readeUserByMail(@PathVariable String email);


  @GetMapping("/users/{id}")
  User readeUser(@PathVariable int id);

  @PutMapping("/users/{id}")
  void updateUser(@PathVariable int id,@RequestBody User user);


  @DeleteMapping("/users/{id}")
  void deleteUser(@PathVariable int id);


}
