package view.TopMenuBar.EditMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditButton {
    JMenu menu;
    DrawingCanvas canvas;

    public EditButton(DrawingCanvas canvas) {
        this.canvas = canvas;
        RedoButton redoButton = new RedoButton(this.canvas);
        UndoButton undoButton = new UndoButton(this.canvas);

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
