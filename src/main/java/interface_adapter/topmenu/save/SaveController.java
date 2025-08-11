package interface_adapter.topmenu.save;


import use_case.topmenu.save.SaveInputBoundary;
import use_case.topmenu.save.SaveInputData;

import java.awt.image.BufferedImage;
import java.io.File;

public class SaveController {
    SaveInputBoundary interactor;
    public SaveController(SaveInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleSaveButtonPress(BufferedImage image, File file){
        SaveInputData inputData = new SaveInputData(image, file);
        interactor.save(inputData);
    }
}
