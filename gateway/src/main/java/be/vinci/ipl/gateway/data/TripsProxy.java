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
@FeignClient(name = "trips")
public interface TripsProxy {

@PostMapping("/trips")
  void createTrip(@RequestBody NewTrip newTrip);


//pas sur de la façon dont j'ai fait tt ça tout param sont optionels ici
  // avec des Querry apram
@GetMapping("/trips")
Iterable<Trip> getListTrips(@QueryParam("departure_date") String departureDate,
    @QueryParam("originLon") String originLon, @QueryParam("destinationLat") String destinationLat,
    @QueryParam("destinationLon") String destinationLon);


@GetMapping("/trips/{id}")
  Trip getTripInfo(@PathVariable int id);

@DeleteMapping("trips/{id}")
  void deleteTrip(@PathVariable int id);

@GetMapping("/trips/{id}/passengers")
Iterable<Passengers> getPassengerList(@PathVariable int id);

@PostMapping("/trips/{trip_id}/passengers/{user_id}")
  void addPasengerToTrip(@PathVariable int trip_id, @PathVariable int user_id);

@GetMapping("/trips/{trip_id}/passengers/{user_id}")
  String getPassengerStatus(@PathVariable int trip_id, @PathVariable int user_id);

@PutMapping("/trips/{trip_id}/passengers/{user_id}")
  void updatePassengerStatus(@PathVariable int trip_id, @PathVariable int user_id, @RequestParam("status") String status);

@DeleteMapping("/trips/{trip_id}/passengers/{user_id}")
  void removePassengerFromTrip(@PathVariable int trip_id, @PathVariable int user_id);
}
