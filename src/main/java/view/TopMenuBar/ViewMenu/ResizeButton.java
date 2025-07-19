package view.TopMenuBar.ViewMenu;

import entity.CanvasState;
import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ResizeButton extends JMenu {

    public ResizeButton(CanvasState canvasState, DrawingCanvas drawingCanvas) {
        super("Resize Canvas");
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(canvasState, drawingCanvas).getMenuItem());
        add(new ZoomOutButton(canvasState, drawingCanvas).getMenuItem());
    }
}
