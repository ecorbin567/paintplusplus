package view.TopMenuBar.ViewMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ZoomInButton {
    private final JMenuItem menuItem;
    public ZoomInButton(DrawingCanvas drawingCanvas){
        menuItem = new JMenuItem("Zoom In");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom in");

        menuItem.addActionListener(e -> {
            drawingCanvas.setScale(drawingCanvas.getScale() * 1.1);
            drawingCanvas.revalidate();
            drawingCanvas.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
