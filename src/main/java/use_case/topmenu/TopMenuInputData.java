package use_case.topmenu;

import entity.ToolEnum;

import java.awt.image.BufferedImage;
import java.io.File;

public class TopMenuInputData {
    private final ToolEnum toolName;
    private final BufferedImage image;
    private final File file;
    private final String currentUsername;

    public TopMenuInputData(ToolEnum toolName, BufferedImage image, File file){
        this.toolName = toolName;
        this.image = image;
        this.file = file;
        this.currentUsername = null;
    }

    public TopMenuInputData(ToolEnum toolName, BufferedImage image, File file, String currentUsername){
        this.toolName = toolName;
        this.image = image;
        this.file = file;
        this.currentUsername = currentUsername;
    }

    public TopMenuInputData(ToolEnum toolName){
        this.toolName = toolName;
        this.image = null;
        this.file = null;
        this.currentUsername = null;
    }
    public ToolEnum getToolName() {
        return toolName;
    }
    public BufferedImage getImage() {
        return image;
    }

    public File getFile() {
        return file;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
