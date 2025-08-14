package interface_adapter.image;

import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.import_image.ImportPresenter;
import org.junit.jupiter.api.Test;
import use_case.image.import_image.ImportResponseModel;
import entity.Image;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportPresenterTest {

    @Test
    void present_setsImageList_onViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        ImportPresenter presenter = new ImportPresenter(vm);

        List<Image> images = new ArrayList<>();
        ImportResponseModel response = new ImportResponseModel(images);

        presenter.present(response);

        assertSame(images, vm.getImageList(),
                "DrawingViewModel should receive the same list from the response model");
    }

    @Test
    void presentError_showsDialog_and_doesNotMutateViewModel() {
        DrawingViewModel vm = new DrawingViewModel();
        ImportPresenter presenter = new ImportPresenter(vm);

        List<Image> beforeRef = vm.getImageList();

        assertDoesNotThrow(() -> presenter.presentError("Any error message"));

        assertSame(beforeRef, vm.getImageList(),
                "presentError should not change the DrawingViewModel's image list");
    }
}
