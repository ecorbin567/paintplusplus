package view.TopMenuBar.ViewMenu;

import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewButton {
    private final JMenu menu;
    public ViewButton(DrawingView drawingView, CanvasController canvasController) {
        ResizeCanvasButton resizeButton = new ResizeCanvasButton(drawingView, canvasController);
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.setActionCommand("view");
        menu.add(resizeButton);
    }

    public JMenu getViewMenu() {
        return menu;
    }
}
