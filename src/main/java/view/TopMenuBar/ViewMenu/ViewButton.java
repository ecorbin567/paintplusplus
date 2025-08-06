package view.TopMenuBar.ViewMenu;

import entity.DrawingCanvas;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewButton {
    private final JMenu menu;
    private final CanvasController canvasController;
    public ViewButton(CanvasController canvasController) {
        this.canvasController = canvasController;
        ResizeCanvasButton resizeButton = new ResizeCanvasButton(this.canvasController);
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.setActionCommand("view");
        menu.add(resizeButton);
    }

    public JMenu getViewMenu() {
        return menu;
    }
}
