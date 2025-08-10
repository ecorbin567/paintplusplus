package view.TopMenuBar.ViewMenu;

import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ResizeCanvasButton extends JMenu {
    public ResizeCanvasButton(DrawingView drawingView, CanvasController canvasController) {
        super("Resize Canvas");
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(drawingView, canvasController).getMenuItem());
        add(new ZoomOutButton(drawingView, canvasController).getMenuItem());
    }
}
