package view.topmenubar.editmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Menu item for redoing the last undone action.
 */
public class RedoButton {
    private final JMenuItem menuItem;

    /**
     * Creates the redo menu item.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
    public RedoButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(evt -> {
            controller.redo();
            drawingView.repaint();
        });
        menuItem.setActionCommand("redo");
    }

    /**
     * @return the redo menu item
     */
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
