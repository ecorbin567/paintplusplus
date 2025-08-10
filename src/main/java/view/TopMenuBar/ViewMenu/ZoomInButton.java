package view.TopMenuBar.ViewMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ZoomInButton {
    private ToolEnum toolName = ToolEnum.ZOOMIN;
    private final JMenuItem menuItem;

    public ZoomInButton(DrawingView drawingView, CanvasController canvasController){
        menuItem = new JMenuItem("Zoom In");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom in");

        menuItem.addActionListener(e -> {
            canvasController.handleZoomInButtonPress(toolName);
            drawingView.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
