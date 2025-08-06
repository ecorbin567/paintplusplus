package view.TopMenuBar.ViewMenu;

import entity.DrawingCanvas;
import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ZoomOutButton {
    private final ToolEnum toolName = ToolEnum.ZOOMOUT;
    private final JMenuItem menuItem;
    private final CanvasController canvasController;

    public ZoomOutButton(CanvasController canvasController){
        this.canvasController = canvasController;
        menuItem = new JMenuItem("Zoom Out");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom Out");

        menuItem.addActionListener(e -> {
            this.canvasController.handleZoomOutButtonPress(toolName);
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
