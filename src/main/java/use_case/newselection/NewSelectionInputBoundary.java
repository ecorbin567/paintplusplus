package use_case.newselection;

/**
 * Input Boundary for actions related to creating a new selection
 */
public interface NewSelectionInputBoundary {
    /**
     * Executes the new selection use case.
     *
     * @param inputData the input data.
     */
    void execute(NewSelectionInputData inputData);
}
