package use_case.getcanvas;

/**
 * Output Data for the Login Use Case.
 */
public class GetCanvasOutputData {

    private final String username;

    public GetCanvasOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
