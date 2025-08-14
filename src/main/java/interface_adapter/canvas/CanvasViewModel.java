package interface_adapter.canvas;

import java.awt.image.BufferedImage;

import interface_adapter.ViewModel;

/**
 * The View Model for the Canvas View.
 */
public class CanvasViewModel extends ViewModel<CanvasUserState> {

    public CanvasViewModel() {
        super("canvas");
        setState(new CanvasUserState());
    }

    public void setInitialImportedImage(BufferedImage initialImportedImage) {
        this.getState().setInitialImportedImage(initialImportedImage);
        firePropertyChanged("import");
    }
}
