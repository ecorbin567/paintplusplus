package use_case.getcanvas;

/**
 * The Input Data for the Login Use Case.
 */
public class GetCanvasInputData {

    private final String username;
    private final String password;

    public GetCanvasInputData(String username, String password) {
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
