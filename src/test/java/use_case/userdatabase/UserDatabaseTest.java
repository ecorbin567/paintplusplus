package use_case.userdatabase;

import data_access.SupabaseAccountRepository;
import entity.CommonUser;
import entity.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserDatabaseTest {
    SupabaseAccountRepository service = new SupabaseAccountRepository();
    String testUserName = "JaneDoe12345";
    String testPswd = "security1";

    @Test
    public void testUserInsertDelete() {
        User user = new CommonUser(testUserName, testPswd);
        boolean result = service.addUser(user);
        assertTrue(result);
        System.out.println("Added user " + user.getUsername() + " / " + user.password());

        // clean up
        boolean delResult = service.deleteUser(user.getUsername());
        assertTrue(delResult);
        System.out.println("Deleted user " + user.getUsername() + " / " + user.password());
    }

    @Test
    public void testUserGet() {
        User user = service.getUser("beabadoobee");
        assertNotNull(user);
        System.out.println("Testing user retrieval: Found: " + user.getUsername() + " / " + user.password());
    }

    @Test
    public void testUserUpdate() {
        User user = createTempUser();

        // update user
        boolean result = service.updateUserPassword(user.getUsername(), "abcdefgh");
        assertTrue(result);

        // check if updated user in the db is different
        User updatedUser = service.getUser(user.getUsername());
        assertNotNull(updatedUser);
        assertEquals(user.getUsername(), updatedUser.getUsername());
        assertNotEquals(updatedUser.password(), user.password());

        // clean up
        deleteTempUser(user);
    }

    @Test
    public void testVerifyCredentials() {
        User user = createTempUser();

        // basic case
        assertTrue(service.verifyCredentials(user.getUsername(), user.password()));

        // update user
        service.updateUserPassword(user.getUsername(), "abcdefgh");

        // case 2: changed password
        assertFalse(service.verifyCredentials(user.getUsername(), user.password()));

        deleteTempUser(user);
    }

    private void deleteTempUser(User user) {
        boolean delResult = service.deleteUser(user.getUsername());
        assertTrue(delResult);
        System.out.println("Deleted user " + user.getUsername() + " / " + user.password());
    }

    private User createTempUser() {
        User user = new CommonUser(testUserName, testPswd);
        boolean result = service.addUser(user);
        assertTrue(result);
        System.out.println("Added user " + user.getUsername() + " / " + user.password());

        return user;
    }

}
