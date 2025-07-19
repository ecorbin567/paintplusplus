package view.TopMenuBar.ViewMenu;

import entity.CanvasState;
import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ZoomOutButton {
    private final JMenuItem menuItem;
    public ZoomOutButton(CanvasState canvasState, DrawingCanvas drawingCanvas){
        menuItem = new JMenuItem("Zoom In");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom in");

        menuItem.addActionListener(e -> {
            canvasState.setScale(canvasState.getScale() / 1.1);
            drawingCanvas.revalidate();
            drawingCanvas.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
