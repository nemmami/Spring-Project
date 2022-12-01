package be.vinci.ipl.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createOne(@RequestBody User user) {
        if (user.getEmail() == null ||
                user.getLastname() == null || user.getFirstname() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        boolean created = service.createOne(user);
        if (!created) throw new ResponseStatusException(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public User readOne(@PathVariable int id) {
        User user = service.readOne(id);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user;
    }

    @GetMapping("/users/{email}")
    public User readOneEmail(@PathVariable String email) {
        User user = service.readOneEmail(email);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user;
    }

    @PutMapping("/users/{id}")
    public void updateOne(@PathVariable int id, @RequestBody User user) {
        if (user.getEmail() == null || 
                user.getLastname() == null || user.getFirstname() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        boolean found = service.updateOne(user);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public void deleteOne(@PathVariable int id) {
        boolean found = service.deleteOne(id);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
