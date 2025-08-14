package interface_adapter.image;

import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.crop.CropPresenter;
import org.junit.jupiter.api.Test;
import use_case.image.crop.CropResponseModel;
import entity.Image;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CropPresenterTest {

    @Test
    void present_setsImageList_onViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        CropPresenter presenter = new CropPresenter(vm);

        // We don't need real images to verify assignment; an empty list is fine.
        List<Image> images = new ArrayList<>();
        CropResponseModel response = new CropResponseModel(images);

        presenter.present(response);

        // Presenter should forward the list directly to the VM.
        assertSame(images, vm.getImageList(), "DrawingViewModel should receive the same list from the response model");
    }

    @Test
    void presentError_showsDialog_and_doesNotMutateViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        CropPresenter presenter = new CropPresenter(vm);

        // Sanity: VM should start with an empty list
        List<Image> before = vm.getImageList();

        // We can’t easily capture JOptionPane in a unit test without a UI harness,
        // but we can at least ensure no exception is thrown and the VM isn’t mutated.
        assertDoesNotThrow(() -> presenter.presentError("Oops"));

        assertSame(before, vm.getImageList(), "presentError should not change the DrawingViewModel state");
    }
}
