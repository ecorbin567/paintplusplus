package view.TopMenuBar.EditMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class RedoButton{
    JMenuItem menuItem;
    DrawingCanvas canvas;

    public RedoButton(DrawingCanvas drawingCanvas) {
        canvas = drawingCanvas;
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                KeyEvent.SHIFT_DOWN_MASK | KeyEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(evt -> canvas.redo());
        menuItem.setActionCommand("redo");
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
