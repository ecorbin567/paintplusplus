package interface_adapter.newselection;

import use_case.newselection.NewSelectionInputBoundary;
import use_case.newselection.NewSelectionInputData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionController {
    private final NewSelectionInputBoundary interactor;

    public SelectionController(NewSelectionInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void start(Point p) {
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START, p, null));
    }

    public void drag(Point p) {
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG, p, null));
    }

    public void commit(Point p, BufferedImage baseImage) {
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, p, baseImage));
    }

    public void cancel() {
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.CANCEL, null, null));
    }
}
