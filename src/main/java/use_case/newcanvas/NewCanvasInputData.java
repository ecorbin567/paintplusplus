package use_case.newcanvas;

/**
 * The Input Data for the New Canvas Use Case.
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
