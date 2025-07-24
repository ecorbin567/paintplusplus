package interface_adapter.canvas;

/**
 * The State information representing the logged-in user.
 */
public class CanvasState {
    private String username = "";

    private String password = "";
    private String passwordError;

    public CanvasState(CanvasState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CanvasState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }
}
