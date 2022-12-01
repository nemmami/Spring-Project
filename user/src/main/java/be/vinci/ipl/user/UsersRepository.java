package be.vinci.ipl.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {

 Optional <User> findByEmail(String email);
}
