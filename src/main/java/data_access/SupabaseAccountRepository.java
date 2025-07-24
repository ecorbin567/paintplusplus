package data_access;

import entity.ActionHistory;
import entity.User;

import java.util.List;

/**
 * In supabase, store json of user data, user action history
 */

// TODO: COMPLETELY UNIMPLEMENTED!!!!

public class SupabaseAccountRepository implements UserDataAccessInterface {

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        return false;
    }

    @Override
    public boolean verifyCredentials(String username, String password) {
        return false;
    }

    @Override
    public boolean saveCanvas(User user, ActionHistory actionHistory) {
        return false;
    }

    @Override
    public ActionHistory findCanvasById(User user, int id) {
        return null;
    }

    @Override
    public List<ActionHistory> getAllCanvases(User user) {
        return List.of();
    }
}
