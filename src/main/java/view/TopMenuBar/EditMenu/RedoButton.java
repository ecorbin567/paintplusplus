package view.TopMenuBar.EditMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class RedoButton{
    private final ToolEnum toolName = ToolEnum.REDO;
    private final JMenuItem menuItem;
    private final CanvasController canvasController;

    public RedoButton(CanvasController canvasController) {
        this.canvasController = canvasController;
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                KeyEvent.SHIFT_DOWN_MASK | KeyEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(evt -> this.canvasController.handleRedoButtonPress(toolName));
        menuItem.setActionCommand("redo");
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
