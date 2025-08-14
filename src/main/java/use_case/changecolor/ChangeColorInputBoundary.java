package use_case.changecolor;

/**
 * Input Boundary for actions which are related to changing colour.
 */
public interface ChangeColorInputBoundary {

    /**
     * Changes the paintbrush color.
     *
     * @param input a new color input.
     */
    void changeColor(ChangeColorInputData input);

    /**
     * Updates the current tool's context.
     *
     * @param input a new color input.
     */
    void setTool(ChangeColorInputData input);

    /**
     * Records which UI color button initiated the change.
     *
     * @param input a new color input.
     */
    void setButton(ChangeColorInputData input);
}
