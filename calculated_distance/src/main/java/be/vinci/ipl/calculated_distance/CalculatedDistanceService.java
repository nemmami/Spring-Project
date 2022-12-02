package be.vinci.ipl.calculated_distance;

import be.vinci.ipl.calculated_distance.data.TripsProxy;
import be.vinci.ipl.calculated_distance.models.Trip;
import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

@Service
public class CalculatedDistanceService {
    public CalculatedDistanceService() {
        ;
    }

    public double getDistance(double origin_lat, double origin_long, double dest_lat, double dest_long) {
        return (abs(origin_lat - origin_long)
                + abs(dest_lat - dest_long));
    }
}
