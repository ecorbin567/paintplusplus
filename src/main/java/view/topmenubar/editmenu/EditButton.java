package view.topmenubar.editmenu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

public class EditButton {
    private final JMenu menu;

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

    public JMenu getEditMenu() {
        return menu;
    }
}
