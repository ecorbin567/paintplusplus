package data_access;

import entity.User;

public interface UserDataAccessInterface {

    /**
     * Saves a new user to the persistent storage.
     * @param user the user to save
     * @return true if successfully saved, false if user already exists
     */
    boolean createUser(User user);

    /**
     * Fetches a user by username.
     * @param username the unique username
     * @return the User object, or null if not found
     */
    User getUser(String username);

    /**
     * Updates an existing user’s data (e.g., password, settings).
     * @param user the updated user object
     * @return true if update was successful
     */
    boolean updateUser(User user);

    /**
     * Deletes a user from persistent storage.
     * @param username the username of the user to delete
     * @return true if deleted successfully
     */
    boolean deleteUser(String username);

    /**
     * Authenticates user login credentials.
     * @param username the input username
     * @param password the input password (already hashed or plain, up to your design)
     * @return true if credentials are correct
     */
    boolean verifyCredentials(String username, String password);
}

