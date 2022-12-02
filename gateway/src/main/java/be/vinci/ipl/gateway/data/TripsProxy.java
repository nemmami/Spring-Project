package be.vinci.ipl.gateway.data;

import be.vinci.ipl.gateway.models.*;
import jakarta.ws.rs.QueryParam;
import java.time.LocalDate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient(name = "trips")
public interface TripsProxy {

@PostMapping("/trips")
  Trip createTrip(@RequestBody NewTrip newTrip);


@GetMapping("/trips")
Iterable<Trip> getListTrips(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
    @RequestParam(required = false) Double origineLat,
    @RequestParam(required = false) Double origineLon,
    @RequestParam(required = false) Double destinationLat,
    @RequestParam(required = false) Double destinationLon);


@GetMapping("/trips/{id}")
  Trip getTripInfo(@PathVariable int id);

@DeleteMapping("trips/{id}")
  void deleteTrip(@PathVariable int id);

@GetMapping("/trips/driver/{id}")
  Iterable<Trip> getAllTripsWhereUserIsDriver(@PathVariable int id);

@DeleteMapping("/trips/driver/{id}")
  void deleteAllTripsWhereUserIsDriver(@PathVariable int id);
}
