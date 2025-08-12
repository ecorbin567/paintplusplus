package interface_adapter.topmenu.canvassize;

import interface_adapter.canvas.DrawingViewModel;
import org.junit.jupiter.api.Test;
import use_case.topmenu.canvassize.SizeOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResizeCanvasPresenterTest {

    @Test
    void setScale_updatesViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        ResizeCanvasPresenter presenter = new ResizeCanvasPresenter(vm);

        presenter.setScale(new SizeOutputData(1.25));

        assertEquals(1.25, vm.getScale(), 1e-9);
    }

    @Test
    void setScale_whenCalledMultipleTimes_reflectsLatestValue() {
        DrawingViewModel vm = new DrawingViewModel();
        ResizeCanvasPresenter presenter = new ResizeCanvasPresenter(vm);

        presenter.setScale(new SizeOutputData(0.9));
        assertEquals(0.9, vm.getScale(), 1e-9);

        presenter.setScale(new SizeOutputData(1.3));
        assertEquals(1.3, vm.getScale(), 1e-9);
    }
}
