package view.TopMenuBar.EditMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UndoButton implements ActionListener {
    JMenuItem menuItem;
    DrawingCanvas canvas;

    public UndoButton(DrawingCanvas canvas) {
        this.canvas = canvas;
        menuItem = new JMenuItem("Undo");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Undo");
        menuItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        menuItem.addActionListener(evt -> canvas.undo());
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
