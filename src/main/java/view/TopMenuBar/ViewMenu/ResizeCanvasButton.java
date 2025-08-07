package view.TopMenuBar.ViewMenu;

import interface_adapter.canvas.CanvasController;
import view.CanvasView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ResizeCanvasButton extends JMenu {
    public ResizeCanvasButton(CanvasView canvasView, CanvasController canvasController) {
        super("Resize Canvas");
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(canvasView, canvasController).getMenuItem());
        add(new ZoomOutButton(canvasView, canvasController).getMenuItem());
    }
}
