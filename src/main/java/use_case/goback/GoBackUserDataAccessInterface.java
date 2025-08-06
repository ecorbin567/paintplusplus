package use_case.goback;

import data_access.UserDataAccessInterface;
import entity.User;

/**
 * DAO for the Go Back Use Case.
 */
public interface GoBackUserDataAccessInterface extends UserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     * @return if add user success
     */
    boolean addUser(User user);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User getUser(String username);

    /**
     * Sets the current user to a given name.
     * @param name the username to set
     */
    void setCurrentUser(String name);
    // JOSH: why is this a String? it works tho so I'll stick with it

    /**
     * Returns the current user's name.
     * @return name the username of the current user
     */
    String getCurrentUser();
}
