package view.TopMenuBar.EditMenu;

import interface_adapter.canvas.CanvasController;
import view.CanvasView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditButton {
    private final JMenu menu;

    public EditButton(CanvasView canvasView, CanvasController controller) {
        RedoButton redoButton = new RedoButton(canvasView, controller);
        UndoButton undoButton = new UndoButton(canvasView, controller);

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
