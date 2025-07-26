package use_case.changecolor;

import java.awt.*;

public class ChangeColorInputData {
    private final Color newColor;

    public ChangeColorInputData(Color newColor){
        this.newColor = newColor;
    }

    public Color getNewColor(){
        return newColor;
    }
}
