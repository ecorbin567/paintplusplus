package view.TopMenuBar.EditMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;
import view.CanvasView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class UndoButton {
    private static final ToolEnum toolName = ToolEnum.UNDO;
    private final JMenuItem menuItem;

    public UndoButton(CanvasView canvasView, CanvasController canvasController) {
        menuItem = new JMenuItem("Undo");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Undo");
        menuItem.addActionListener(evt -> {
            canvasController.handleUndoButtonPress(toolName);
            canvasView.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
