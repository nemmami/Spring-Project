package be.vinci.ipl.trips;

import be.vinci.ipl.trips.models.NoIdTrip;
import be.vinci.ipl.trips.models.Trip;
import feign.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.Path;

@RestController
public class TripsController {

    private final TripsService service;

    public TripsController(TripsService service) {
        this.service = service;
    }

    @PostMapping("/trips")
    public ResponseEntity<Trip> createOne(@RequestBody NoIdTrip trip) {
        if(trip.getDepartureDate() == null
                || trip.getOrigin().getLatitude() < 0 || trip.getOrigin().getLongitude() < 0
                || trip.getDestination().getLatitude() < 0 || trip.getDestination().getLongitude() < 0
                || trip.getDriverId() < 0 || trip.getAvailableSeatigng() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        System.out.println(trip);
        Trip createdTrip = service.createOne(trip);
        System.out.println(createdTrip);
        return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
    }

    @GetMapping("/trips/{id}")
    public Trip readOne(@PathVariable int id) {
        Trip trip = service.readOne(id);
        if(trip == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return trip;
    }

    @DeleteMapping("/trips/{id}")
    public void deleteOne(@PathVariable int id) {
        if(!service.deleteOne(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/trips/driver/{id}")
    public Iterable<Trip> readFromDriver(@PathVariable int id) {
        return service.readFromDiver(id);
    }

    @DeleteMapping("/trips/driver/{id}")
    public void deleteFromDriver(@PathVariable int id) {
        service.deleteFromDriver(id);
    }

    @GetMapping("/trips")
    public Iterable<Trip> find20First() {
        return service.find20First();
    }

    /* test
    @GetMapping("/calculatedDistance/{id}")
    public double getDistance(@PathVariable int id) {
        double distance = service.getDistance(id);
        if(distance == -1)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return distance;
    }
    */
}
