package app;

import entity.CanvasState;
import interface_adapter.changecolor.ColorController;
import use_case.changecolor.ChangeColorInputBoundary;
import use_case.changecolor.ChangeColorInteractor;

public class ColorUseCaseFactory {
    /**
     * Factory function for creating the Change Colour Use Case.
     * @param canvasState the CanvasState
     * @return the ColorController created
     */
    public static ColorController create(CanvasState canvasState) {
        final ChangeColorInputBoundary interactor = new ChangeColorInteractor(canvasState);
        return new ColorController(interactor);
    }

}
