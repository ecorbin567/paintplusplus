package view.TopMenuBar.ViewMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ZoomOutButton {
    private final ToolEnum toolName = ToolEnum.ZOOMOUT;
    private final JMenuItem menuItem;

    public ZoomOutButton(DrawingView drawingView, CanvasController canvasController){
        menuItem = new JMenuItem("Zoom Out");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom Out");

        menuItem.addActionListener(e -> {
            canvasController.handleZoomOutButtonPress(toolName);
            drawingView.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
