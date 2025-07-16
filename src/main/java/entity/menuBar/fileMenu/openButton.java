package entity.menuBar.fileMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class openButton implements ActionListener{
    JMenuItem menuItem;

    public openButton() {
        menuItem = new JMenuItem("Open");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_DOWN_MASK));
        menuItem.setActionCommand("open");
        menuItem.addActionListener(this);
    }
    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public void handleAction() {

    }

    public void actionPerformed(ActionEvent e) {
        handleAction();
    }
}
