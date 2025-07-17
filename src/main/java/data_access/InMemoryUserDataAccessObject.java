package data_access;

import entity.User;

import java.util.HashMap;
import java.util.Map;

/** In memory database for testing users and user information */
public class InMemoryUserDataAccessObject implements UserDataAccessInterface {
    private Map<String, User> usersMap;

    public InMemoryUserDataAccessObject() {
        this.usersMap = new HashMap<>();

        // creating users for testing
        createUser(new User("beabadoobee", "abcdefg123"));
    }

    /* DEPENDENCY INJECTIONS!!!! :DDDDD */
    public boolean createUser(User user) {
        if (usersMap.get(user.getUsername()) == null) {
            usersMap.put(user.getUsername(), user);
            return true;
        } else {
            return false;
        }
    }

    // null case is considered in interface?
    public User getUser(String username) {
        return usersMap.get(username);
    }


    public boolean updateUser(User user) {
        if (usersMap.get(user.getUsername()) != null) {
            usersMap.remove(user.getUsername());
            usersMap.put(user.getUsername(), user);
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteUser(String username) {
        if (usersMap.get(username) != null) {
            usersMap.remove(username);
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyCredentials(String username, String password) {
        if (usersMap.get(username) != null) {
            User u = usersMap.get(username);
            return password.equals(u.getPassword());
        } else {
            return false;
        }
    }
}
