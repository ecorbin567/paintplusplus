package use_case.changecolor;

public interface ChangeColorInputBoundary {
    void changeColor(ChangeColorInputData input);
    void setTool(ChangeColorInputData input);
    void setButton(ChangeColorInputData input);
}
