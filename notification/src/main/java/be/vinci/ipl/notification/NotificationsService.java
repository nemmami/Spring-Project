package be.vinci.ipl.notification;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationsService {

    private final NotificationRepository repository;

    public NotificationsService(NotificationRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a notification
     * @param n notification to create,id of the user
     * @return true if the notification could be created, false if another notification exists with this trip_id and user_id
     */
    public boolean createOne(Notification n,int id) {
        /* if (repository.existsById(n.getId()))return false; */
        n.setUserId(id);
        repository.save(n);
        return true;
    }

    /**
     * Reads all notification of a user
     * @param id id of the user
     * @return Notifications of a user , or null if the user couldn't be found
     */
    public ArrayList<Notification> read(int id) {
        return repository.findAllByUserId(id);
    }

    /**
     * Deletes all notification of a user
     * @param id id of the user
     * @return True if all notification of a user could be deleted, false if the user couldn't be found
     */
    public boolean delete(int id) {
        if (!repository.existsByUserId(id)) return false;
        repository.deleteByUserId(id);
        return true;
    }

}
