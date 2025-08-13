package data_access;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import use_case.newcanvas.NewCanvasUserDataAccessInterface;

public class SupabaseCanvasRepository implements NewCanvasUserDataAccessInterface {

    private static final String CANVAS_DATABASE_URL = "https:"
            + "//jrzhzrsourpuiflfzdgc.supabase.co";
    private static final String API_KEY =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                    + "eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6"
                    + "Impyemh6cnNvdXJwdWlmbGZ6ZGdjIiwicm"
                    + "9sZSI6ImFub24iLCJpYXQiOjE3NTMzOTgzNz"
                    + "IsImV4cCI6MjA2ODk3NDM3Mn0.410etuDXZxk"
                    + "RBwnHi76oei7I_djIJWFw3e3RkL6Aw3I";
    private static final String BUCKET_NAME = "canvasimages";
    private static final String BACKSLASH = "/";

    private void uploadImage(String parentFolderName, BufferedImage image) {
        // 1. Convert BufferedImage to byte array
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);

            final byte[] imageData = baos.toByteArray();

            // 2. Construct a unique filename
            final String fileName = parentFolderName + BACKSLASH + UUID.randomUUID() + ".png";

            // 3. Create upload URL
            final URL url = new URL(
                    CANVAS_DATABASE_URL
                            + "/storage/v1/object/" + BUCKET_NAME + BACKSLASH + fileName);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 4. Configure request
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "image/png");

            // 5. Write image data to request body
            try (OutputStream os = connection.getOutputStream()) {
                os.write(imageData);
            }

            // 6. Handle response
            final int responseCode = connection.getResponseCode();

            if (responseCode >= HttpResponseCodes.SUCCESS_OK && responseCode < HttpResponseCodes.REDIRECTION_MC) {
                System.out.println("Upload successful: " + fileName);
            }
            else {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    in.lines().forEach(System.err::println);
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static List<BufferedImage> getImages(String username) {
        try {
            final List<String> imagePaths = listImagePaths(username);
            final List<BufferedImage> images = new ArrayList<>();

            for (String path : imagePaths) {
                final BufferedImage img = downloadImage(username + BACKSLASH + path);
                if (img != null) {
                    images.add(img);
                }
            }

            return images;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static List<String> listImagePaths(String username) throws IOException {
        final String listUrl = CANVAS_DATABASE_URL + "/storage/v1/object/list/" + BUCKET_NAME;

        final HttpURLConnection conn = (HttpURLConnection) new URL(listUrl).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        final String jsonBody = "{\"prefix\": \"" + username + "/\"}";
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonBody.getBytes());
        }

        List<String> paths = new ArrayList<>();
        final int status = conn.getResponseCode();
        if (status == HttpResponseCodes.SUCCESS_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                final String response = reader.lines().reduce("", (aaa, bbb) -> aaa + bbb);
                paths = parsePathsFromJson(response);
            }
        }
        else {
            throw new IOException("Failed to list images: " + status);
        }

        return paths;
    }

    private static List<String> parsePathsFromJson(String json) {
        // Extremely primitive path extraction (you may use a real JSON lib instead)
        final List<String> paths = new ArrayList<>();
        int idx = 0;
        while ((idx = json.indexOf("\"name\":\"", idx)) != -1) {
            idx += HttpResponseCodes.EIGHT;
            final int end = json.indexOf("\"", idx);
            final String path = json.substring(idx, end);
            if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg")) {
                paths.add(path);
            }
            idx = end;
        }
        return paths;
    }

    private static BufferedImage downloadImage(String path) {
        BufferedImage toReturn;
        final String imageUrl = CANVAS_DATABASE_URL + "/storage/v1/object/public/" + BUCKET_NAME + BACKSLASH + path;
        try (InputStream in = new URL(imageUrl).openStream()) {
            toReturn = ImageIO.read(in);
        }
        catch (IOException ex) {
            System.err.println("Failed to download: " + imageUrl);
            toReturn = null;
        }
        return toReturn;
    }

    @Override
    public boolean saveCanvas(String username, BufferedImage image) {
        uploadImage(username, image);
        return true;
    }

    @Override
    public List<BufferedImage> getAllCanvases(String username) {
        return getImages(username);
    }
}
