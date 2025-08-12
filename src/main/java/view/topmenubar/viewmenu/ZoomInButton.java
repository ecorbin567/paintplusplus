package view.topmenubar.viewmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Menu item for zooming in on the canvas.
 */
public class ZoomInButton {
    private final JMenuItem menuItem;

    /**
     * Creates the zoom in menu item.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
    public ZoomInButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Zoom In");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, InputEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom in");

        menuItem.addActionListener(e -> {
            controller.zoomIn();
            drawingView.repaint();
        });
    }

    /**
     * @return the zoom in menu item
     */
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
