package view.TopMenuBar.EditMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;
import view.CanvasView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RedoButton{
    private static final ToolEnum toolName = ToolEnum.REDO;
    private final JMenuItem menuItem;

    public RedoButton(CanvasView canvasView, CanvasController canvasController) {
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(evt -> {
            canvasController.handleRedoButtonPress(toolName);
            canvasView.repaint();
        });
        menuItem.setActionCommand("redo");
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
