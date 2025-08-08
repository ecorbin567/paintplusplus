package interface_adapter.goback;

/**
 * The state for the Go Back View Model.
 */
public class GoBackState {
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
