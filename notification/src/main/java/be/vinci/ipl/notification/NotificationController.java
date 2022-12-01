package be.vinci.ipl.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
public class NotificationController {

    private final NotificationsService service;

    public NotificationController(NotificationsService service) {
        this.service = service;
    }

    @PostMapping("/notification/users/{id}")
    public ResponseEntity<Void> createOne(@PathVariable int id,@RequestBody Notification n) {
        if (n.getNotificationText() == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        boolean created = service.createOne(n,id);
        if (!created) throw new ResponseStatusException(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/notification/users/{id}")
    public ArrayList<Notification> read(@PathVariable int id) { //status 200 array json
        ArrayList<Notification> n = service.read(id);
        if (n == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return n;
    }

    @DeleteMapping("/notification/users/{id}")
    public void delete(@PathVariable int id) {
        boolean found = service.delete(id);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
