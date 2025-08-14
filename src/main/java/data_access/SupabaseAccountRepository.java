package data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.CommonUser;
import entity.User;
import use_case.goback.GoBackUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * User accounts data access object.
 */
public class SupabaseAccountRepository implements UserDataAccessInterface,
        LoginUserDataAccessInterface, SignupUserDataAccessInterface, GoBackUserDataAccessInterface {
    // You should never store API keys like this. But I don't care.
    private final String apiKey = "sb_secret_ZDGh4GSj6nne_LGHTJ162A__CFUb7sF";
    private final String userDatabaseUrl = "https://jrzhzrsourpuiflfzdgc.supabase.co/rest/v1/Users";

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
        return getUser(username) != null;
    }

    @Override
    public void setCurrentUser(String name) {
        currentUser = name;
    }

    @Override
    public boolean verifyCredentials(String username, String password) {
        final User user = getUser(username);
        return user != null && password.equals(user.password());
    }

    @Override
    public String getCurrentUser() {
        return currentUser;
    }

    // primary data access interface methods
    @Override
    public boolean addUser(User user) {
        boolean result;
        try {
            // Convert the user to a JSON string
            final JSONObject json = EntityToJSONConverter.convertUserToJSON(user);

            // Build the request
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(userDatabaseUrl))
                    .header(HttpResponseCodes.APIKEY, apiKey)
                    .header(HttpResponseCodes.AUTHORIZATION, HttpResponseCodes.BEARER + apiKey)
                    .header("Content-Type", HttpResponseCodes.CONTENT_TYPE_JSON)
                    .header("Prefer", "return=representation")
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .build();

            // Send the request
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if insert succeeded
            result = response.statusCode() == HttpResponseCodes.SUCCESS_CREATED;

        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            result = false;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            result = false;
        }

        return result;
    }

    @Override
    public User getUser(String username) {
        User userToReturn;

        try {
            // Supabase filter format: ?username=eq.<value>
            final String encodedUsername = encodeUsername(username);
            final String url = userDatabaseUrl + "?username=" + encodedUsername;

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header(HttpResponseCodes.APIKEY, apiKey)
                    .header(HttpResponseCodes.AUTHORIZATION, HttpResponseCodes.BEARER + apiKey)
                    .header("Accept", HttpResponseCodes.CONTENT_TYPE_JSON)
                    .GET()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpResponseCodes.SUCCESS_OK) {
                final JSONArray results = new JSONArray(response.body());
                if (results.length() > 0) {
                    final JSONObject userJson = results.getJSONObject(0);
                    userToReturn = EntityToJSONConverter.convertJSONToUser(userJson);
                }
                else {
                    // user not found
                    userToReturn = null;
                }
            }
            else {
                System.err.println("Error: " + response.statusCode() + "\n" + response.body());
                userToReturn = null;
            }

        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            userToReturn = null;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            userToReturn = null;
        }

        return userToReturn;
    }

    @Override
    public boolean updateUserPassword(String username, String newPassword) {
        final boolean status1 = deleteUser(username);
        final boolean status2 = addUser(new CommonUser(username, newPassword));
        return status1 && status2;
    }

    @Override
    public boolean deleteUser(String username) {
        boolean result;
        try {
            // Supabase filter format: ?username=eq.<value>
            final String encodedUsername = encodeUsername(username);
            final String url = userDatabaseUrl + "?username=" + encodedUsername;

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header(HttpResponseCodes.APIKEY, apiKey)
                    .header(HttpResponseCodes.AUTHORIZATION, HttpResponseCodes.BEARER + apiKey)
                    .header("Content-Type", HttpResponseCodes.CONTENT_TYPE_JSON)
                    .DELETE()
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            result = response.statusCode() == HttpResponseCodes.SUCCESS_NO_CONTENT;
            // 204 No Content = successfully deleted

        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            result = false;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            result = false;
        }
        return result;
    }
}
