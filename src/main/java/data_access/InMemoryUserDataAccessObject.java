package data_access;

import entity.ActionHistory;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** In memory database for testing users and user information */
public class InMemoryUserDataAccessObject implements UserDataAccessInterface {
    private Map<String, User> usersMap;

    /** Map usernames to list of their action history. */
    private Map<String, List<ActionHistory>> usersDocumentsMap;

    public InMemoryUserDataAccessObject() {
        this.usersMap = new HashMap<>();
        this.usersDocumentsMap = new HashMap<>();

        // creating users for testing
        String username = "beabadoobee";
        String password = "abcdefg123";
        addUser(new User(username, password));


    }

    /* DEPENDENCY INJECTIONS!!!! :DDDDD */
    public boolean addUser(User user) {
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

    @Override
    public boolean saveCanvas(User user, ActionHistory actionHistory) {
        if (usersDocumentsMap.get(user.getUsername()) == null) {
            usersDocumentsMap.put(user.getUsername(), new ArrayList<>());
            usersDocumentsMap.get(user.getUsername()).add(actionHistory);
            return true;
        } else {
            usersDocumentsMap.get(user.getUsername()).add(actionHistory);
            return false;
        }

    }

    @Override
    public ActionHistory findCanvasById(User user, int id) {
        return usersDocumentsMap.get(user.getUsername()).get(id);
    }

    @Override
    public List<ActionHistory> getAllCanvases(User user) {
        return usersDocumentsMap.get(user.getUsername());
    }


}
