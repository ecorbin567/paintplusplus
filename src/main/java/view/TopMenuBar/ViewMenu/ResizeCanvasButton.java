package view.TopMenuBar.ViewMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ResizeCanvasButton extends JMenu {

    public ResizeCanvasButton(DrawingCanvas drawingCanvas) {
        super("Resize Canvas");
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(drawingCanvas).getMenuItem());
        add(new ZoomOutButton(drawingCanvas).getMenuItem());
    }
}
