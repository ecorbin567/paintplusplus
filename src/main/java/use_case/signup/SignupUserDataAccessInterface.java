package use_case.signup;

import data_access.UserDataAccessInterface;
import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface extends UserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     *
     * @param user the user to save
     */
    boolean addUser(User user);
}
