package data_access;

import entity.CommonUser;
import entity.User;

public class TestUserRepositoryMethods {
    private static final UserDataAccessInterface service = new SupabaseAccountRepository();

    public static void main(String[] args) {
        TestAddUser("alice");
        TestAddUser("Miguel");
        TestGetUser("Miguel");
        TestDeleteUser("alice");
    }

    public static void TestAddUser(String username) {
        User user = new CommonUser(username, "plaintext12345");
        boolean result = service.addUser(user);

        if (result) {
            System.out.println("✅ User added successfully");
        } else {
            System.out.println("❌ Failed to add user");
        }
    }

    public static void TestGetUser(String username) {
        // test user get
        User user = service.getUser(username);

        if (user != null) {
            System.out.println("Found: " + user.getUsername() + " / " + user.getPassword());
        } else {
            System.out.println("User not found.");
        }
    }

    public static void TestDeleteUser(String username) {
        boolean result = service.deleteUser(username);

        if (result) {
            System.out.println("✅ User deleted successfully");
        } else {
            System.out.println("❌ Failed to delete user");
        }
    }

}