package interface_adapter.image;

import entity.Image;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.rotate.RotatePresenter;
import org.junit.jupiter.api.Test;
import use_case.image.rotate.RotateResponseModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RotatePresenterTest {

    @Test
    void present_setsImageList_onViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        RotatePresenter presenter = new RotatePresenter(vm);

        List<Image> rotatedImages = new ArrayList<>();
        // Adjust constructor if your RotateResponseModel differs
        RotateResponseModel response = new RotateResponseModel(rotatedImages);

        presenter.present(response);

        assertSame(rotatedImages, vm.getImageList(),
                "DrawingViewModel should receive the images from the rotate response");
    }

    @Test
    void presentError_showsDialog_and_doesNotMutateViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        RotatePresenter presenter = new RotatePresenter(vm);

        List<Image> before = vm.getImageList();

        assertDoesNotThrow(() -> presenter.presentError("rotate failed"));

        assertSame(before, vm.getImageList(),
                "presentError should not change the DrawingViewModel state");
    }
}
