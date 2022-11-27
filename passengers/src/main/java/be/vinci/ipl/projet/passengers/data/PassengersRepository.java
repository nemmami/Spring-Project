package be.vinci.ipl.projet.passengers.data;

import be.vinci.ipl.projet.passengers.models.Passenger;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengersRepository extends CrudRepository<Passenger, Long> {

  boolean existsByTripIdAndUserId(long tripId, long userId);

  Optional<Passenger> findByTripIdAndUserId(long tripId, long userId);

  @Transactional
  void deleteByTripIdAndUserId(long tripId, long userId);

  Iterable<Passenger> findByTripId(long tripId);

}
