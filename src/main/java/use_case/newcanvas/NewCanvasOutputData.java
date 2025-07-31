package use_case.newcanvas;

/**
 * Output Data for the Login Use Case.
 */
public class NewCanvasOutputData {

    private final String username;

    public NewCanvasOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
