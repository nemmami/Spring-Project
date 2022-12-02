package be.vinci.ipl.trips;

import be.vinci.ipl.trips.data.CalculatedDistanceProxy;
import be.vinci.ipl.trips.models.NoIdTrip;
import be.vinci.ipl.trips.models.Position;
import be.vinci.ipl.trips.models.PositionHolder;
import be.vinci.ipl.trips.models.Trip;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class TripsController {

    private final TripsService service;
    private final CalculatedDistanceProxy calculatedDistanceProxy;

    public TripsController(TripsService service, CalculatedDistanceProxy calculatedDistanceProxy) {
        this.service = service;
        this.calculatedDistanceProxy = calculatedDistanceProxy;
    }

    @PostMapping("/trips")
    public ResponseEntity<Trip> createOne(@RequestBody NoIdTrip trip) {
        if(trip.getDepartureDate() == null
                || trip.getOrigin().getLatitude() < 0 || trip.getOrigin().getLongitude() < 0
                || trip.getDestination().getLatitude() < 0 || trip.getDestination().getLongitude() < 0
                || trip.getDriverId() < 0 || trip.getAvailableSeating() < 0)
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
    @ResponseBody
    public Iterable<Trip> find20First(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(required = false) Double origineLat,
            @RequestParam(required = false) Double origineLon,
            @RequestParam(required = false) Double destinationLat,
            @RequestParam(required = false) Double destinationLon
    ) {
        Iterable<Trip> firsts = service.find20First();

        if(origineLat != null && origineLon != null && destinationLat != null && destinationLon != null) { // case : both origine and dest in query
            return StreamSupport.stream(firsts.spliterator(), false)
                    .sorted((o1, o2) -> (int) compare(
                            new PositionHolder(new Position(origineLat, origineLon),
                                    new Position(o1.getOrigin().getLatitude(), o1.getOrigin().getLongitude())),
                            new PositionHolder(new Position(destinationLat, destinationLon),
                                    new Position(o2.getDestination().getLatitude(), o2.getDestination().getLongitude()))))
                    .toList();
        } else if(destinationLat != null && destinationLon != null) { // case : only dest in query
            return StreamSupport.stream(firsts.spliterator(), false)
                    .sorted((o1, o2) -> (int) compare(
                            new PositionHolder(new Position(destinationLat, destinationLon),
                                    new Position(o1.getDestination().getLatitude(), o1.getDestination().getLongitude())),
                            new PositionHolder(new Position(destinationLat, destinationLon),
                                    new Position(o2.getDestination().getLatitude(), o2.getDestination().getLongitude()))))
                    .toList();
        } else if(origineLat != null && origineLon != null) { // case : only origine in query
            return StreamSupport.stream(firsts.spliterator(), false)
                    .sorted((o1, o2) -> (int) compare(
                            new PositionHolder(new Position(origineLat, origineLon),
                                    new Position(o1.getOrigin().getLatitude(), o1.getOrigin().getLongitude())),
                            new PositionHolder(new Position(origineLat, origineLon),
                                    new Position(o2.getOrigin().getLatitude(), o2.getOrigin().getLongitude()))))
                    .toList();
        } else if(departureDate != null) { //case : date in query
            return StreamSupport.stream(firsts.spliterator(), false)
                    .filter(e ->
                            e.getDepartureDate().isEqual(departureDate)
                    )
                    .toList();
        }

        return firsts;
    }

    public double compare(PositionHolder pos1, PositionHolder pos2) {
        double dist1 = calculatedDistanceProxy.getDistance(pos1);
        double dist2 = calculatedDistanceProxy.getDistance(pos2);
        return Double.compare(dist2, dist1);
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
