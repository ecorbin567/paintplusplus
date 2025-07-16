package entity.menuBar.editMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class editButton {
    JMenu menu;

    public editButton() {
        redoButton redoButton = new redoButton();
        undoButton undoButton = new undoButton();

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
