package be.vinci.ipl.user;

import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository repository;

    public UsersService(UsersRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a user
     * @param user User to create
     * @return true if the user could be created, false if another user exists with this pseudo
     */
    public User createOne(User user) {
        if (repository.existsById(user.getId())) return null;
        repository.save(user);
        return user;
    }

    /**
     * Reads a user
     * @param id id of the user
     * @return The user found, or null if the user couldn't be found
     */
    public User readOne(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Reads a user
     * @param email id of the user
     * @return The user found, or null if the user couldn't be found
     */
    public User readOneEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    /**
     * Updates a user
     * @param user User to update
     * @return True if the user could be updated, false if the user couldn't be found
     */
    public boolean updateOne(User user) {
        if (!repository.existsById(user.getId())) return false;
        repository.save(user);
        return true;
    }

    /**
     * Deletes a user
     * @param id id of the user
     * @return True if the user could be deleted, false if the user couldn't be found
     */
    public boolean deleteOne(int id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

}
