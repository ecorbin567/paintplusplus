package interface_adapter.topmenu;

import java.awt.image.BufferedImage;
import java.io.File;

public interface TopMenuFacade {
    void zoomIn();
    void zoomOut();
    void save(BufferedImage image, File file);
    void undo();
    void redo();
}
