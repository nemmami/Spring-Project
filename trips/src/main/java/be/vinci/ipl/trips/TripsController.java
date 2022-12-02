package be.vinci.ipl.trips;

import be.vinci.ipl.trips.data.CalculatedDistanceProxy;
import be.vinci.ipl.trips.models.NoIdTrip;
import be.vinci.ipl.trips.models.Position;
import be.vinci.ipl.trips.models.PositionHolder;
import be.vinci.ipl.trips.models.Trip;
import feign.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.Path;
import java.time.LocalDate;
import java.util.Comparator;
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
    @ResponseBody
    public Iterable<Trip> find20First(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) List<Double> origine,
            @RequestParam(required = false) List<Double> dest
    ) {
        Iterable<Trip> firsts = service.find20First();

        if(origine.size() == 2 && dest.size() == 2) { // case : both origine and dest in query

        } else if(dest.size() == 2) { // case : only dest in query (dest[0] == lat, dest[1] == lon)

        } else if(origine.size() == 2) { // case : only origine in query (origin[0] == lat, origin[1] == lon)
            /* -> not working
            return StreamSupport.stream(firsts.spliterator(), false)
                    .sorted((o1, o2) -> compareOrigin(
                            new PositionHolder(new Position((double)origine.toArray()[0], (double)origine.toArray()[1]),
                                    new Position(o1.getOrigin().getLatitude(), o1.getOrigin().getLongitude())),
                            new PositionHolder(new Position((double)origine.toArray()[0], (double)origine.toArray()[1]),
                                    new Position(o2.getOrigin().getLatitude(), o2.getOrigin().getLongitude())))
                    .toList();

             */
        } else if(date != null) { //case : date in query
            return StreamSupport.stream(firsts.spliterator(), false)
                    .filter(e ->
                            e.getDepartureDate().isEqual(date)
                    )
                    .toList();
        }

        return firsts;
    }

    public double compareOrigin(PositionHolder pos1, PositionHolder pos2) {
        double dist1 = calculatedDistanceProxy.getDistance(pos1);
        double dist2 = calculatedDistanceProxy.getDistance(pos2);
        return Double.compare(dist1, dist2);
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
