package use_case.changecolor;

/**
 * Input Boundary for actions which are related to changing colour.
 */
public interface ChangeColorInputBoundary {
    void changeColor(ChangeColorInputData input);
    void setTool(ChangeColorInputData input);
    void setButton(ChangeColorInputData input);
}
