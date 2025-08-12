package view.topmenubar.viewmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Menu for resizing the canvas.
 */
public class ResizeCanvasButton extends JMenu {

    /**
     * Creates the resize canvas menu with zoom in and zoom out options.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
    public ResizeCanvasButton(DrawingView drawingView, TopMenuFacade controller) {
        super("Resize Canvas");
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(drawingView, controller).getMenuItem());
        add(new ZoomOutButton(drawingView, controller).getMenuItem());
    }
}
