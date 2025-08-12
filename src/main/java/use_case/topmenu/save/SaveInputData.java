package use_case.topmenu.save;

import java.awt.image.BufferedImage;
import java.io.File;

public record SaveInputData(BufferedImage image, File file) {
}
