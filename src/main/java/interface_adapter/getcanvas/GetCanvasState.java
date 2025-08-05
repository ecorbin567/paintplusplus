package interface_adapter.getcanvas;

/**
 * The state for the Login View Model.
 */
public class GetCanvasState {
    private String username = "";
    private String password = "";

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
