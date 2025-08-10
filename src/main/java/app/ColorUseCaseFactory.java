package app;

import entity.CanvasState;
import interface_adapter.changecolor.ColorController;
import use_case.changecolor.ChangeColorInputBoundary;
import use_case.changecolor.ChangeColorInteractor;

public class ColorUseCaseFactory {
    public static ColorController create(CanvasState canvasState){
        ChangeColorInputBoundary interactor = new ChangeColorInteractor(canvasState);
        return new ColorController(interactor);
    }

}
