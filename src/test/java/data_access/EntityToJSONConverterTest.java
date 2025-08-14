package data_access;

import entity.ActionHistory;
import entity.CommonUser;
import entity.StrokeRecord;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityToJSONConverterTest {

    @Test
    void convertUserToJSON() {
        User u = new CommonUser("alice", "secret");
        JSONObject json = EntityToJSONConverter.convertUserToJSON(u);
        assertEquals("alice", json.getString("username"));
        assertEquals("secret", json.getString("password"));
    }

    @Test
    void convertJSONToUser() {
        JSONObject json = new JSONObject()
                .put("username", "bob")
                .put("password", "pw");
        User u = EntityToJSONConverter.convertJSONToUser(json);
        assertEquals("bob", u.getUsername());
        assertEquals("pw", u.password());
    }

    @Test
    void convertStrokeRecordToJSON() {
        List<Point> pts = Arrays.asList(new Point(1, 2), new Point(3, 4), new Point(5, 6));
        Color colour = new Color(255, 0, 170);
        float width = 5.5f;
        StrokeRecord stroke = new StrokeRecord(colour, width);

        JSONObject json = EntityToJSONConverter.convertStrokeRecordToJSON(stroke);

        assertEquals("#ff00aa", json.getString("colour"));
        assertEquals(width, json.getDouble("width"), 1e-6);
        JSONArray arr = json.getJSONArray("points");
    }

    @Test
    void convertActionHistoryToJSON() {
        ActionHistory history = new ActionHistory();

        List<Point> undoPts = Arrays.asList(new Point(10, 11), new Point(12, 13));
        StrokeRecord undoStroke = new StrokeRecord(new Color(0, 128, 255), 3f);
        history.getUndoStack().push(undoStroke);

        List<Point> curPts = Arrays.asList(new Point(7, 8), new Point(9, 10));
        StrokeRecord currentStroke = new StrokeRecord(new Color(34, 17, 221), 7f);
        history.setCurrentState(currentStroke);

        JSONObject json = EntityToJSONConverter.convertActionHistoryToJSON(history);

        JSONArray undo = json.getJSONArray("undoStack");
        assertEquals(1, undo.length());
    }
}
