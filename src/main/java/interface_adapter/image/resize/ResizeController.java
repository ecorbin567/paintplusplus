package interface_adapter.image.resize;

import use_case.image.resize.ResizeInputBoundary;
import use_case.image.resize.ResizeRequestModel;

public class ResizeController {
    private final ResizeInputBoundary interactor;

    public ResizeController(ResizeInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(int newWidth, int newHeight) {
        final ResizeRequestModel request = new ResizeRequestModel(newWidth, newHeight);
        interactor.execute(request);
    }
}
