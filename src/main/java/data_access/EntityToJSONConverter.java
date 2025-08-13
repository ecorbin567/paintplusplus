package data_access;

import java.awt.Color;
import java.awt.Point;
import java.util.Deque;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.ActionHistory;
import entity.CommonUser;
import entity.Drawable;
import entity.StrokeRecord;
import entity.User;

public final class EntityToJSONConverter {

    private EntityToJSONConverter() {
        // To Prevent Instantiation
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
        // TODO: Delete these
        final JSONObject result = new JSONObject();

        // Serialize undo stack
        final JSONArray undoStackJson = new JSONArray();
        final Deque<Drawable> undoStack = history.getUndoStack();
        for (Drawable d : undoStack) {
            if (d instanceof StrokeRecord strokeRecord) {
                undoStackJson.put(convertStrokeRecordToJSON(strokeRecord));
            }
        }
        result.put("undoStack", undoStackJson);

        // Serialize current state
        final Drawable current = history.getCurrentState();
        if (current instanceof StrokeRecord strokeRecord) {
            result.put("currentState", convertStrokeRecordToJSON(strokeRecord));
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

    private static String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
