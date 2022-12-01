package be.vinci.ipl.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    boolean existsByUserId(Integer user);

    ArrayList<Notification> findAllByUserId(Integer user);

    void deleteByUserId(Integer id);

}
