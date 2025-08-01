package data_access;

import entity.ActionHistory;
import entity.CommonUser;
import entity.User;
import use_case.goback.GoBackUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.newcanvas.NewCanvasUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
                                                     LoginUserDataAccessInterface,
                                                     NewCanvasUserDataAccessInterface,
        GoBackUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, List<ActionHistory>> usersDocumentsMap = new HashMap<>();
    private String currentUser;

    public InMemoryUserDataAccessObject() {

        // creating users for testing
        //createUser(new User("beabadoobee", "abcdefg123"));
    }

    /* DEPENDENCY INJECTIONS!!!! :DDDDD */
    public boolean createUser(User user) {
        if (users.get(user.getUsername()) == null) {
            users.put(user.getUsername(), user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public boolean addUser(User user) {
        users.put(user.getName(), user);
        return true;
    }

    @Override
    public boolean updateUserPassword(String username, String newPassword) {
        if (users.get(username) == null) {
            return false;
        } else {
            users.remove(username);
            users.put(username, new CommonUser(username, newPassword));
            return true;
        }
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

    public boolean deleteUser(String username) {
        if (users.get(username) != null) {
            users.remove(username);
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyCredentials(String username, String password) {
        if (users.get(username) != null) {
            User u = users.get(username);
            return password.equals(u.getPassword());
        } else {
            return false;
        }
        }

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

    public ActionHistory findCanvasById(User user, int id) {
        return usersDocumentsMap.get(user.getUsername()).get(id);
    }

    public List<ActionHistory> getAllCanvases(User user) {
        return usersDocumentsMap.get(user.getUsername());
    }
}
