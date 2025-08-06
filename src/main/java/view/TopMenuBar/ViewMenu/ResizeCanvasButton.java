package view.TopMenuBar.ViewMenu;

import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ResizeCanvasButton extends JMenu {
    CanvasController canvasController;

    public ResizeCanvasButton(CanvasController canvasController) {
        super("Resize Canvas");
        this.canvasController = canvasController;
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(this.canvasController).getMenuItem());
        add(new ZoomOutButton(this.canvasController).getMenuItem());
    }
}
