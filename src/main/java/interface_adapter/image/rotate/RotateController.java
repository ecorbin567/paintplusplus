package interface_adapter.midmenu.image.rotate;

import use_case.image.rotate.RotateInputBoundary;
import use_case.image.rotate.RotateRequestModel;

public class RotateController {
    private final RotateInputBoundary interactor;

    public RotateController(RotateInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(double degrees) {
        RotateRequestModel request = new RotateRequestModel(degrees);
        interactor.execute(request);
    }
}
