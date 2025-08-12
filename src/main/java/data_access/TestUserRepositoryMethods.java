package data_access;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import entity.CommonUser;
import entity.User;
import use_case.newcanvas.NewCanvasUserDataAccessInterface;

public class TestUserRepositoryMethods {
    private static final UserDataAccessInterface service = new SupabaseAccountRepository();
    private static final NewCanvasUserDataAccessInterface canvasService = new SupabaseCanvasRepository();

    public static void main(String[] args) throws IOException {
        TestRetrieveCanvasImages();
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

    public static void TestUpdateUserPswd() {
        User user = new CommonUser("abady", "plainy");
        boolean result = service.addUser(user);
        if (result) {
            System.out.println("✅ User added successfully");
        } else {
            System.out.println("❌ Failed to add user");
        }

        String newPassword = "swaggy";
        service.updateUserPassword(user.getUsername(), newPassword);
        User retrievedUser = service.getUser(user.getUsername());

        if (newPassword.equals(retrievedUser.getPassword())) {
            System.out.println("✅ User password updated to: " + retrievedUser.getPassword());
        } else {
            System.out.println("❌ Failed to update user");
        }
    }

    public static void TestCanvasUpload() throws IOException {

        InputStream is = TestUserRepositoryMethods.class.getResource("/images/wheel.png").openStream();
        BufferedImage image = ImageIO.read(is);

        canvasService.saveCanvas("beabadoobee", image);
    }

    public static void TestRetrieveCanvasImages() throws IOException {
        String username = "beabadoobee";

        List<BufferedImage> images = canvasService.getAllCanvases(username);

        // Assert we retrieved at least one image
        if (images == null) {
            System.out.println("Image list should not be null");
            return;
        } else if (images.isEmpty()) {
            System.out.println("No images retrieved for user: " + username);
            return;
        }

        // Write each image to disk so the tester can visually inspect them
        int i = 1;
        for (BufferedImage img : images) {
            if (img == null) {
                System.out.println("One of the retrieved images is null");
            }

            File output = new File(System.getProperty("java.io.tmpdir"), "retrieved_image_" + i + ".png");
            ImageIO.write(img, "png", output);
            System.out.println("Wrote image to: " + output.getAbsolutePath());
            i++;
        }
    }
}
