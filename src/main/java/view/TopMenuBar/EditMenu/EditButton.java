package view.TopMenuBar.EditMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditButton {
    JMenu menu;

    public EditButton() {
        RedoButton redoButton = new RedoButton();
        UndoButton undoButton = new UndoButton();

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
