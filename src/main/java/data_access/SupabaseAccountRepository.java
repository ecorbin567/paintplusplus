package data_access;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import entity.ActionHistory;
import entity.CommonUser;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.goback.GoBackUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

/**
 * In supabase, store json of user data, user action history
 */

// TODO: COMPLETELY UNIMPLEMENTED!!!!
//  TODO: user get and set methods
//  TODO: verify and stuff

public class SupabaseAccountRepository implements UserDataAccessInterface,
        LoginUserDataAccessInterface, SignupUserDataAccessInterface, GoBackUserDataAccessInterface {
    // You should never store API keys like this. But I don't care.
    private final String API_KEY = "sb_secret_ZDGh4GSj6nne_LGHTJ162A__CFUb7sF";
    private final String USER_DATABASE_URL = "https://jrzhzrsourpuiflfzdgc.supabase.co/rest/v1/Users";

    private final HttpClient client;
    private String currentUser = "";

    public SupabaseAccountRepository() {
        client = HttpClient.newHttpClient();
    }

    private String encodeUsername(String username) {
        return java.net.URLEncoder.encode("eq." + username, java.nio.charset.StandardCharsets.UTF_8);
    }

    // the following 3 methods are from signup/login data access interfaces.
    @Override
    public boolean existsByName(String username) {
        return (getUser(username) != null);
    }

    @Override
    public void setCurrentUser(String name) {
        currentUser = name;
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }

    // primary data access interface methods
    @Override
    public boolean addUser(User user) {
        try {
            // Convert the user to a JSON string
            JSONObject json = EntityToJSONConverter.convertUserToJSON(user);

            // Build the request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(USER_DATABASE_URL))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=representation")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            // Send the request
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response);

            // Check if insert succeeded
            return response.statusCode() == 201;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUser(String username) {
        try {
            // Supabase filter format: ?username=eq.<value>
            String encodedUsername = encodeUsername(username);
            String url = USER_DATABASE_URL + "?username=" + encodedUsername;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONArray results = new JSONArray(response.body());
                if (results.length() > 0) {
                    JSONObject userJson = results.getJSONObject(0);
                    return EntityToJSONConverter.convertJSONToUser(userJson);
                } else {
                    return null; // user not found
                }
            } else {
                System.err.println("Error: " + response.statusCode() + "\n" + response.body());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateUserPassword(String username, String newPassword) {
        try {
            boolean status1 = deleteUser(username);
            boolean status2 = addUser(new CommonUser(username, newPassword));
            return status1 && status2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(String username) {
        try {
            // Supabase filter format: ?username=eq.<value>
            String encodedUsername = encodeUsername(username);
            String url = USER_DATABASE_URL + "?username=" + encodedUsername;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 204; // 204 No Content = successfully deleted

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean verifyCredentials(String username, String password) {
        User user = getUser(username);
        return user != null && password.equals(user.getPassword());
    }

    // TODO: canvas methods are unimplemented

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
