package use_case.topmenu.canvassize;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.topmenu.canvassize.ResizeCanvasPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SizeInteractorTest {

    private CanvasState canvasState;
    private SizeOutputBoundary presenter;
    private SizeInteractor interactor;
    private DrawingViewModel drawingViewModel;

    @BeforeEach
    void setUp() {
        drawingViewModel = new DrawingViewModel();
        canvasState = new CanvasState();
        presenter = new ResizeCanvasPresenter(drawingViewModel);
        interactor = new SizeInteractor(canvasState, presenter);

        canvasState.setScale(1.0);
    }

    @Test
    void zoomIn_increasesScaleByPointOne_andUpdatesViewModel() {
        interactor.zoomIn();

        assertEquals(1.1, canvasState.getScale());
        assertEquals(1.1, drawingViewModel.getScale());
    }

    @Test
    void zoomOut_decreasesScaleByPointOne_andUpdatesViewModel() {
        interactor.zoomOut();

        assertEquals(0.9, canvasState.getScale());
        assertEquals(0.9, drawingViewModel.getScale());
    }
}
