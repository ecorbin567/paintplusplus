package entity;

/**
 * A simple implementation of the User interface.
 */
public record CommonUser(String name, String password) implements User {

    @Override
    public String getUsername() {
        return name();
    }

}
