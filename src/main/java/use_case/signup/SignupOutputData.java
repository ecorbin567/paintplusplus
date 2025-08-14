package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 *
 * @param username      username
 * @param useCaseFailed use success
 */
public record SignupOutputData(String username, boolean useCaseFailed) {

}
