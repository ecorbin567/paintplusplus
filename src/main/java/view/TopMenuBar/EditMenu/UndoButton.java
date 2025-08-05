package view.TopMenuBar.EditMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class UndoButton {
    private final ToolEnum toolName = ToolEnum.UNDO;
    private final JMenuItem menuItem;
    private final CanvasController canvasController;

    public UndoButton(CanvasController canvasController) {
        this.canvasController = canvasController;
        menuItem = new JMenuItem("Undo");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Undo");
        menuItem.addActionListener(evt ->
                this.canvasController.handleUndoButtonPress(toolName));
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
