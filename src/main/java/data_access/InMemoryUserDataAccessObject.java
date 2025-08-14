package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.ActionHistory;
import entity.CommonUser;
import entity.User;
import use_case.goback.GoBackUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        GoBackUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<ActionHistory>> usersDocumentsMap = new HashMap<>();
    private String currentUser;

    public InMemoryUserDataAccessObject() {

        // default user for quick login
        createUser(new CommonUser("asdf", "asdf"));
    }

    /* DEPENDENCY INJECTIONS!!!! :DDDDD */

    /**
     * Create user in memory.
     *
     * @param user user
     * @return success
     */
    public boolean createUser(User user) {
        if (users.get(user.getUsername()) == null) {
            users.put(user.getUsername(), user);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public boolean addUser(User user) {
        users.put(user.getUsername(), user);
        return true;
    }

    @Override
    public boolean updateUserPassword(String username, String newPassword) {
        final boolean toReturn;
        if (users.get(username) == null) {
            toReturn = false;
        }
        else {
            users.remove(username);
            users.put(username, new CommonUser(username, newPassword));
            toReturn = true;
        }
        return toReturn;
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public void setCurrentUser(String name) {
        this.currentUser = name;
    }

    @Override
    public String getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Delete user from memory.
     *
     * @param username the username of the user to delete
     * @return success
     */
    public boolean deleteUser(String username) {
        final boolean toReturn;
        if (users.get(username) != null) {
            users.remove(username);
            toReturn = true;
        }
        else {
            toReturn = false;
        }
        return toReturn;
    }

    /**
     * Yes.
     *
     * @param username the input username
     * @param password the input password
     * @return success.
     */
    public boolean verifyCredentials(String username, String password) {
        final boolean toReturn;
        if (users.get(username) != null) {
            final User u = users.get(username);
            toReturn = password.equals(u.password());
        }
        else {
            toReturn = false;
        }
        return toReturn;
    }
}
