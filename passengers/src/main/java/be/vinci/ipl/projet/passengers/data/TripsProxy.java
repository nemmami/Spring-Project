package be.vinci.ipl.projet.passengers.data;

import be.vinci.ipl.projet.passengers.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "trips")
public interface TripsProxy {

  @GetMapping("/trips/{id}")
  User readOne(@PathVariable int id);

}
