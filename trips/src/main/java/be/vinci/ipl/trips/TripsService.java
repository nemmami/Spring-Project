package be.vinci.ipl.trips;

import be.vinci.ipl.trips.data.TripsRepository;
import be.vinci.ipl.trips.data.UsersProxy;
import be.vinci.ipl.trips.models.NoIdTrip;
import be.vinci.ipl.trips.models.Trip;
import be.vinci.ipl.trips.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.stream.StreamSupport;

import static java.lang.Math.abs;

@Service
public class TripsService {
    private final TripsRepository repository;
    private final UsersProxy usersProxy;

    public TripsService(TripsRepository repository, UsersProxy usersProxy) {
        this.repository = repository;
        this.usersProxy = usersProxy;
    }

    /**
     * Creates a trips in repository
     * @param noIdTrip Trip to create
     * @return The trip created with its id
     */
    public Trip createOne(NoIdTrip noIdTrip) {
        return repository.save(noIdTrip.toTrip());
    }

    /**
     * Reads a trip in repository
     * @param id ID of the trip
     * @return The trip, or null if it couldn't be found
     */
    public Trip readOne(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Deletes a trip form repository
     * @param id ID of the trip
     * @return True if the trip was deleted, false if it couldn't be found
     */
    public boolean deleteOne(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteTripById(id);
        return true;
    }

    /**
     * Deletes all trips of a driver
     * @param driverId ID of the driver
     */
    public void deleteFromDriver(int driverId) {
        repository.deleteTripByDriverId(driverId);
    }

    /**
     * Reads all trips of a driver
     * @param driverId ID of the driver
     * @return the list of his trips
     */
    public Iterable<Trip> readFromDiver(int driverId) {
        return repository.findByDriverId(driverId);
    }

    /**
     * Finds 20 last trips
     * @return the list of trips
     */
    public Iterable<Trip> find20First() {
        //return repository.findAll();
        Iterable<Trip> firsts = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return StreamSupport.stream(firsts.spliterator(), false)
                .filter(first ->
                    first.getAvailableSeatigng() > 0
                )
                .limit(20)
                .toList();
    }

    /* test
    public double getDistance(int id) {
        Trip trip = readOne(id);
        if(trip == null)
            return -1;
        return (abs(trip.getOrigin().getLatitude() - trip.getOrigin().getLongitude())
                + abs(trip.getDestination().getLatitude() - trip.getDestination().getLongitude()));
    }
    */
}
