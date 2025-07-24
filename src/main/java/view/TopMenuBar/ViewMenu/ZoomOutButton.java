package view.TopMenuBar.ViewMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ZoomOutButton {
    private final JMenuItem menuItem;
    public ZoomOutButton(DrawingCanvas drawingCanvas){
        menuItem = new JMenuItem("Zoom Out");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom in");

        menuItem.addActionListener(e -> {
            drawingCanvas.setScale(drawingCanvas.getScale() / 1.1);
            drawingCanvas.revalidate();
            drawingCanvas.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
