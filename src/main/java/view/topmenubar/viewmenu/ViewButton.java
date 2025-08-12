package view.topmenubar.viewmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * View menu button.
 */
public class ViewButton {
    private final JMenu menu;

    /**
     * Creates the view menu.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
    public ViewButton(DrawingView drawingView, TopMenuFacade controller) {
        ResizeCanvasButton resizeButton = new ResizeCanvasButton(drawingView, controller);
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.setActionCommand("view");
        menu.add(resizeButton);
    }

    /**
     * @return the view menu
     */
    public JMenu getViewMenu() {
        return menu;
    }
}
