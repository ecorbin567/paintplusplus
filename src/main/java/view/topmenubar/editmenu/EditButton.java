package view.topmenubar.editmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Edit menu button.
 */
public class EditButton {
    private final JMenu menu;

    /**
     * Creates the edit menu with undo and redo options.
     *
     * @param drawingView the drawing view
     * @param controllers the top menu controllers
     */
    public EditButton(DrawingView drawingView, TopMenuFacade controllers) {
        RedoButton redoButton = new RedoButton(drawingView, controllers);
        UndoButton undoButton = new UndoButton(drawingView, controllers);

        JMenuItem undoMenuItem = undoButton.getMenuItem();
        JMenuItem redoMenuItem = redoButton.getMenuItem();

        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menu.setActionCommand("edit");

        menu.add(undoMenuItem);
        menu.add(redoMenuItem);
    }

    /**
     * @return the edit menu
     */
    public JMenu getEditMenu() {
        return menu;
    }
}
