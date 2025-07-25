package interface_adapter.image.crop;

import use_case.image.crop.CropInputBoundary;
import use_case.image.crop.CropRequestModel;

public class CropController {

    private final CropInputBoundary interactor;

    public CropController(CropInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(int x, int y, int width, int height) {
        CropRequestModel request = new CropRequestModel(x, y, width, height);
        interactor.execute(request);
    }
}
