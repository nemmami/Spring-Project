package be.vinci.ipl.calculated_distance;

import be.vinci.ipl.calculated_distance.data.TripsProxy;
import be.vinci.ipl.calculated_distance.models.Position;
import be.vinci.ipl.calculated_distance.models.PositionHolder;
import be.vinci.ipl.calculated_distance.models.Trip;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CalculatedDistanceController {
    private final CalculatedDistanceService service;
    private final TripsProxy tripsProxy;

    public CalculatedDistanceController(CalculatedDistanceService service, TripsProxy tripsProxy) {
        this.service = service;
        this.tripsProxy = tripsProxy;
    }

    // in case we want to calculate distance from a specific trip
    @GetMapping("/calculatedDistance/{id}")
    public double getDistanceFromTrip(@PathVariable int id) {
        Trip trip = tripsProxy.readOne(id);
        if(trip == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return service.getDistance(trip.getOrigin().getLatitude(), trip.getOrigin().getLongitude(),
                trip.getDestination().getLatitude(), trip.getDestination().getLongitude());
    }

    // in case we want to calculate distance between 2 points
    @GetMapping("/calculatedDistance/")
    public double getDistance(@RequestBody PositionHolder p) {
        if(p.getOrigin().getLatitude() < 0 || p.getOrigin().getLongitude() < 0
                || p.getDestination().getLatitude() < 0 || p.getDestination().getLongitude() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return service.getDistance(p.getOrigin().getLatitude(), p.getOrigin().getLongitude(),
                p.getDestination().getLatitude(), p.getDestination().getLongitude());
    }
}
