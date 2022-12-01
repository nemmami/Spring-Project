package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.*;
import javax.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient(name = "trips")
public interface TripsProxy {

@PostMapping("/trips")
  Trip createTrip(@RequestBody NewTrip newTrip);


@GetMapping("/trips")
Iterable<Trip> getListTrips(@QueryParam("departure_date") String departureDate,
    @QueryParam("originLon") String originLon, @QueryParam("destinationLat") String destinationLat,
    @QueryParam("destinationLon") String destinationLon);


@GetMapping("/trips/{id}")
  Trip getTripInfo(@PathVariable int id);

@DeleteMapping("trips/{id}")
  void deleteTrip(@PathVariable int id);

@GetMapping("/trips/driver/{id}")
  Iterable<Trip> getAllTripsWhereUserIsDriver(@PathVariable int id);

@DeleteMapping("/trips/driver/{id}")
  void deleteAllTripsWhereUserIsDriver(@PathVariable int id);
}
