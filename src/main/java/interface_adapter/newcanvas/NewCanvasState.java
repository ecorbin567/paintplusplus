package interface_adapter.newcanvas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Login View Model.
 */
public class NewCanvasState {
    private String username = "";
    private String password = "";
    private List<BufferedImage> canvases = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<BufferedImage> getCanvases() {
        return canvases;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCanvases(List<BufferedImage> canvases) {
        this.canvases = canvases;
    }

    public void addCanvas(BufferedImage canvas) {
        this.canvases.add(canvas);
    }
}
