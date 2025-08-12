package data_access;

import entity.User;

/**
 * Primary interface for accessing user information
 */
public interface UserDataAccessInterface {
    /**
     * Saves a new user to the persistent storage.
     * @param user the user to save
     * @return true if successfully saved, false if user already exists
     */
    boolean addUser(User user);

    /**
     * Fetches a user by username.
     * @param username the unique username
     * @return the User object, or null if not found
     */
    User getUser(String username);

    /**
     * Updates an existing user’s password.
     * @param username the updated username
     * @return true if update was successful
     */
    boolean updateUserPassword(String username, String newPassword);

    /**
     * Deletes a user from persistent storage.
     * @param username the username of the user to delete
     * @return true if deleted successfully
     */
    boolean deleteUser(String username);

    // COMBINED WITH LOGIN/SIGNUP
    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Sets the current user to a given name.
     * @param name the username to set
     */
    void setCurrentUser(String name);
}

