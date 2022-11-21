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
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient(name = "trips")
public interface TripsProxy {

@PostMapping("/trips")
  void createTrip(@RequestBody NewTrip newTrip);


//pas sur de la façon dont j'ai fait tt ça tout param sont optionels ici
@GetMapping("/trips")
  void getListTrips(@RequestParam("departure_date") String departureDate,
    @RequestParam("originLon") String originLon, @RequestParam("destinationLat") String destinationLat,
    @RequestParam("destinationLon") String destinationLon);


@GetMapping("/trips/{id}")
  void getTripInfo(@PathVariable int id);

@DeleteMapping("trips/{id}")
  void deleteTrip(@PathVariable int id);

@GetMapping("/trips/{id}/passengers")
  void getPassengerList(@PathVariable int id);

@PostMapping("/trips/{trip_id}/passengers/{user_id}")
  void addPasengerToTrip(@PathVariable int trip_id, @PathVariable int user_id);

@GetMapping("/trips/{trip_id}/passengers/{user_id}")
  void getPassengerStatus(@PathVariable int trip_id, @PathVariable int user_id);

@PutMapping("/trips/{trip_id}/passengers/{user_id}")
  void updatePassengerStatus(@PathVariable int trip_id, @PathVariable int user_id, @RequestParam("status") String status);

@DeleteMapping("/trips/{trip_id}/passengers/{user_id}")
  void removePassengerFromTrip(@PathVariable int trip_id, @PathVariable int user_id);
}
