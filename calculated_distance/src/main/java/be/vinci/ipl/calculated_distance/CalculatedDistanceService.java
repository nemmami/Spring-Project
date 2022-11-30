package be.vinci.ipl.calculated_distance;

import be.vinci.ipl.calculated_distance.data.TripsProxy;
import be.vinci.ipl.calculated_distance.models.Trip;
import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

@Service
public class CalculatedDistanceService {
    private final TripsProxy tripsProxy;

    public CalculatedDistanceService(TripsProxy tripsProxy) {
        this.tripsProxy = tripsProxy;
    }

    public double getDistance(int id) {
        Trip trip = tripsProxy.readOne(id);
        if(trip == null)
            return -1;
        return (abs(trip.getOrigin().getLatitude() - trip.getOrigin().getLongitude())
                + abs(trip.getDestination().getLatitude() - trip.getDestination().getLongitude()));
    }
}
