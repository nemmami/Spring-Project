package be.vinci.ipl.trips.data;

import be.vinci.ipl.trips.models.Trip;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.Optional;

public interface TripsRepository extends CrudRepository<Trip, Integer> {

    Optional<Trip> findById(int integer);

    Iterable<Trip> findByDriverId(int driverId);

    @Transactional
    void deleteTripById(int id);

    @Transactional
    void deleteTripByDriverId(int driverId);

    Iterable<Trip> findAll(Sort id);
}
