package view.TopMenuBar.EditMenu;

import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditButton {
    private final JMenu menu;

    public EditButton(DrawingView drawingView, CanvasController controller) {
        RedoButton redoButton = new RedoButton(drawingView, controller);
        UndoButton undoButton = new UndoButton(drawingView, controller);

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
