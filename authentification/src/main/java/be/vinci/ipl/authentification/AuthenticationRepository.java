package be.vinci.ipl.authentification;

import be.vinci.ipl.authentification.models.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends CrudRepository<Credentials, String> {
}
