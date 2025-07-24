package data_access;

import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.Stack;

public class EntityToJSONConverter {

    /**
     * Converts a User object into a JSON representation.
     *
     * @param user the User object to convert
     * @return a JSONObject representing the user
     */
    public static JSONObject convertUserToJSON(User user) {
        JSONObject json = new JSONObject();
        json.put("username", user.getUsername());
        json.put("password", user.getPassword());
        return json;
    }

    /**
     * Converts an ActionHistory object into a JSON representation.
     *
     * @param history the ActionHistory of the document to convert
     * @return a JSONObject representing the document
     */
    public static JSONObject convertActionHistoryToJSON(ActionHistory history) {
        // TODO: Must be reconfigured for a general Drawable since this is 
        //  dependent on the Drawable being specifically a StrokeRecord. 
        JSONObject result = new JSONObject();

        // Serialize undo stack
        JSONArray undoStackJson = new JSONArray();
        Stack<Drawable> undoStack = history.getUndoStack();
        for (Drawable d : undoStack) {
            if (d instanceof StrokeRecord) {
                undoStackJson.put(convertStrokeRecordToJSON((StrokeRecord) d));
            }
        }
        result.put("undoStack", undoStackJson);

        // Serialize current state
        Drawable current = history.getCurrentState();
        if (current instanceof StrokeRecord) {
            result.put("currentState", convertStrokeRecordToJSON((StrokeRecord) current));
        } else {
            result.put("currentState", JSONObject.NULL);
        }

        return result;
    }

    public static JSONObject convertStrokeRecordToJSON(StrokeRecord stroke) {
        JSONObject json = new JSONObject();

        // Stroke width
        json.put("width", stroke.width);

        // Colour as hex (e.g., "#ff00aa")
        json.put("colour", colorToHex(stroke.colour));

        // List of points
        JSONArray pointsArray = new JSONArray();
        for (Point pt : stroke.pts) {
            JSONObject pointJson = new JSONObject();
            pointJson.put("x", pt.x);
            pointJson.put("y", pt.y);
            pointsArray.put(pointJson);
        }

        json.put("points", pointsArray);

        return json;
    }

    private static String colorToHex(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    /**
     * Example usage.
     */
    public static void main(String[] args) {
        User user = new CommonUser("alice", "securepassword123");
        JSONObject userJson = convertUserToJSON(user);
        System.out.println(userJson.toString(2)); // Pretty print with indentation
    }
}
