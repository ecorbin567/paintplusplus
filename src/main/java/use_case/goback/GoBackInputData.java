package use_case.goback;

/**
 * The Input Data for the Login Use Case.
 */
public class GoBackInputData {

    private final String username;
    private final String password;

    public GoBackInputData(String username, String password) {
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
