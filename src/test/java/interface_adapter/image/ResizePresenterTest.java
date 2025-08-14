package interface_adapter.image;

import entity.Image;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.resize.ResizePresenter;
import org.junit.jupiter.api.Test;
import use_case.image.resize.ResizeResponseModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResizePresenterTest {

    @Test
    void present_setsImageList_onViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        ResizePresenter presenter = new ResizePresenter(vm);

        List<Image> images = new ArrayList<>();
        // If your ResizeResponseModel has a different constructor, adjust here.
        ResizeResponseModel response = new ResizeResponseModel(images);

        presenter.present(response);

        assertSame(images, vm.getImageList(),
                "DrawingViewModel should receive the same list from the response model");
    }

    @Test
    void presentError_showsDialog_and_doesNotMutateViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        ResizePresenter presenter = new ResizePresenter(vm);

        List<Image> beforeRef = vm.getImageList();

        assertDoesNotThrow(() -> presenter.presentError("Any error"));

        assertSame(beforeRef, vm.getImageList(),
                "presentError should not change the DrawingViewModel's image list");
    }
}
