package entity.menuBar.fileMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class saveAsButton implements ActionListener {

    JMenuItem menuItem;
    public saveAsButton() {
        menuItem = new JMenuItem("Save As");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.ALT_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand("saveAs");
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
