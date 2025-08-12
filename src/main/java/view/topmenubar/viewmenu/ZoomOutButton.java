package view.topmenubar.viewmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Menu item for zooming out of the canvas.
 */
public class ZoomOutButton {
    private final JMenuItem menuItem;

    /**
     * Creates the zoom out menu item.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
    public ZoomOutButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Zoom Out");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom Out");

        menuItem.addActionListener(e -> {
            controller.zoomOut();
            drawingView.repaint();
        });
    }

    /**
     * @return the zoom out menu item
     */
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
