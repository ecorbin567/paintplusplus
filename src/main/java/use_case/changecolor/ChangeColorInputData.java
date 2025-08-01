package use_case.changecolor;

import java.awt.*;

/**
 * The Input Data for the Change Colour Use Case.
 */
public class ChangeColorInputData {
    private final Color newColor;

    public ChangeColorInputData(Color newColor){
        this.newColor = newColor;
    }

    public Color getNewColor(){
        return newColor;
    }
}
