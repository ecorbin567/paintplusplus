package use_case.goback;

/**
 * The output boundary for the Go Back Use Case.
 */
public interface GoBackOutputBoundary {
    void prepareSuccessView(String command, GoBackOutputData goBackOutputData);
}
