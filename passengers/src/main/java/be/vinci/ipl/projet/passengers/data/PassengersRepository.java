package be.vinci.ipl.projet.passengers.data;

import be.vinci.ipl.projet.passengers.models.Passenger;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengersRepository extends CrudRepository<Passenger, Integer> {

  boolean existsByTripIdAndUserId(int tripId, int userId);

  Optional<Passenger> findByTripIdAndUserId(int tripId, int userId);

  @Transactional
  void deleteByTripIdAndUserId(int tripId, int userId);

  Iterable<Passenger> findByTripId(int tripId);

  Iterable<Passenger> findByUserId(int userId);

  @Transactional
  void deleteByTripId(int tripId);

  @Transactional
  void deleteByUserId(long userId);
}
