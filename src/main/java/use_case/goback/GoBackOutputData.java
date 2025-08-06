package use_case.goback;

/**
 * Output Data for the Go Back Use Case.
 */
public class GoBackOutputData {

    private final String username;

    public GoBackOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
