package be.vinci.ipl.trips;

import be.vinci.ipl.trips.data.TripsRepository;
import be.vinci.ipl.trips.data.UsersProxy;
import be.vinci.ipl.trips.models.NoIdTrip;
import be.vinci.ipl.trips.models.Trip;
import be.vinci.ipl.trips.models.User;
import org.springframework.stereotype.Service;

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



}
