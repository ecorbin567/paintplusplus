package entity.TopMenuBar.viewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import entity.CanvasState;

public class ResizeButton implements ActionListener {
    JMenuItem menuItem;
    public ResizeButton() {
        menuItem = new JMenuItem("Resize View");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setActionCommand("resize");
        menuItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        handleAction();
    }

    public void handleAction() {

    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
