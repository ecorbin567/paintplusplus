package interface_adapter.topmenu.save;

import java.awt.image.BufferedImage;
import java.io.File;

import use_case.topmenu.save.SaveInputBoundary;
import use_case.topmenu.save.SaveInputData;

public class SaveController {
    SaveInputBoundary interactor;

    public SaveController(SaveInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleSaveButtonPress(BufferedImage image, File file) {
        final SaveInputData inputData = new SaveInputData(image, file);
        interactor.save(inputData);
    }
}
