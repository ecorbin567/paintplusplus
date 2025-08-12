package view.topmenubar.editmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Menu item for undoing the last action.
 */
public class UndoButton {
    private final JMenuItem menuItem;

    /**
     * Creates the undo menu item.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
    public UndoButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Undo");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Undo");
        menuItem.addActionListener(evt -> {
            controller.undo();
            drawingView.repaint();
        });
    }

    /**
     * @return the undo menu item
     */
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
