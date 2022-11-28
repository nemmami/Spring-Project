package be.vinci.ipl.trips.data;

import be.vinci.ipl.trips.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "users")
public interface UsersProxy {
    @GetMapping("/users/{id}")
    User readOne(@PathVariable int id);
}
