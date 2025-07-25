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
    }
}
