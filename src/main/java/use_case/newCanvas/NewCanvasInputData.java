package use_case.newCanvas;

/**
 * The Input Data for the Login Use Case.
 */
public class NewCanvasInputData {

    private final String username;
    private final String password;

    public NewCanvasInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
