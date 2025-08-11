package view.TopMenuBar.EditMenu;

import interface_adapter.topmenu.TopMenuFacade;
import interface_adapter.topmenu.TopMenuFacadeImpl;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

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
