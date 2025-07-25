package use_case.userdatabase;

import data_access.SupabaseAccountRepository;
import entity.CommonUser;
import entity.User;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InsertUserTest {
    SupabaseAccountRepository service = new SupabaseAccountRepository();

    @Test
    public void testUserInsert() {
        User user = new CommonUser("saliva", "ilovecheetos");
        boolean result = service.addUser(user);
        assertTrue(result);
        System.out.println("Added user " + user.getUsername() + " / " + user.getPassword());
    }

    @Test
    public void testUserGet() {
        User user = service.getUser("alice");
        assertNotNull(user);
        System.out.println("Testing user retrieval: Found: " + user.getUsername() + " / " + user.getPassword());
    }
}
