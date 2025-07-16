package entity.menuBar.fileMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class joinLinkButton implements ActionListener {
    JMenuItem menuItem;

    public joinLinkButton() {
        menuItem = new JMenuItem("Join Link");
        menuItem.setActionCommand("joinLink");
        menuItem.setMnemonic(KeyEvent.VK_J);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.ALT_DOWN_MASK));
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
