package data_access;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import entity.ActionHistory;
import entity.User;
import org.json.JSONObject;

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

public class SupabaseAccountRepository implements UserDataAccessInterface {
    // You should never store API keys like this. But I don't care.
    private final String API_KEY = "sb_secret_ZDGh4GSj6nne_LGHTJ162A__CFUb7sF";
    private final String USER_DATABASE_URL = "https://jrzhzrsourpuiflfzdgc.supabase.co/rest/v1/Users";

    private final HttpClient client;
    private final ObjectMapper mapper;

    public SupabaseAccountRepository() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

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
