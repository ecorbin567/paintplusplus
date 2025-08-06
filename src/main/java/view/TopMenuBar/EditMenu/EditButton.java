package view.TopMenuBar.EditMenu;

import entity.DrawingCanvas;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditButton {
    private final JMenu menu;
    private final CanvasController controller;

    public EditButton(CanvasController controller) {
        this.controller = controller;
        RedoButton redoButton = new RedoButton(this.controller);
        UndoButton undoButton = new UndoButton(this.controller);

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
