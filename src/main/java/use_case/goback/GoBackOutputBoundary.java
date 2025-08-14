package use_case.goback;

/**
 * The output boundary for the Go Back Use Case.
 */
public interface GoBackOutputBoundary {

    /**
     * Presents a successful "go back" result to the UI.
     * @param command the navigation command that was executed.
     * @param goBackOutputData data required to update the view after navigating.
     */
    void prepareSuccessView(String command, GoBackOutputData goBackOutputData);
}
