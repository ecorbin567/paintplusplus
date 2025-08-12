package data_access;

import java.awt.*;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.*;

public class EntityToJSONConverter {

    private EntityToJSONConverter() {
        //To Prevent Instantiation
    }

    /**
     * Converts a User object into a JSON representation.
     *
     * @param user the User object to convert
     * @return a JSONObject representing the user
     */
    public static JSONObject convertUserToJSON(User user) {
        final JSONObject json = new JSONObject();
        json.put("username", user.getUsername());
        json.put("password", user.password());
        return json;
    }

    /**
     * Converts a JSON object into a User.
     *
     * @param json the JSONObject to convert
     * @return the corresponding User
     */
    public static User convertJSONToUser(JSONObject json) {
        final String username = json.getString("username");
        final String password = json.getString("password");
        return new CommonUser(username, password);
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
        final JSONObject result = new JSONObject();

        // Serialize undo stack
        final JSONArray undoStackJson = new JSONArray();
        final Stack<Drawable> undoStack = history.getUndoStack();
        for (Drawable d : undoStack) {
            if (d instanceof StrokeRecord) {
                undoStackJson.put(convertStrokeRecordToJSON((StrokeRecord) d));
            }
        }
        result.put("undoStack", undoStackJson);

        // Serialize current state
        final Drawable current = history.getCurrentState();
        if (current instanceof StrokeRecord) {
            result.put("currentState", convertStrokeRecordToJSON((StrokeRecord) current));
        }
        else {
            result.put("currentState", JSONObject.NULL);
        }

        return result;
    }

    /**
     * Converts a StrokeRecord object into a JSON representation.
     *
     * @param stroke the StrokeRecord object to convert
     * @return a JSONObject representing the StrokeRecord
     */
    public static JSONObject convertStrokeRecordToJSON(StrokeRecord stroke) {
        final JSONObject json = new JSONObject();

        // Stroke width
        json.put("width", stroke.getWidth());

        // Colour as hex (e.g., "#ff00aa")
        json.put("colour", colorToHex(stroke.getColour()));

        // List of points
        final JSONArray pointsArray = new JSONArray();
        for (Point pt : stroke.getPts()) {
            final JSONObject pointJson = new JSONObject();
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
}
