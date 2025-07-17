package entity.TopMenuBar.editMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UndoButton implements ActionListener {
    JMenuItem menuItem;

    public UndoButton() {
        menuItem = new JMenuItem("Undo");
        menuItem.setMnemonic(KeyEvent.VK_Z);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.ALT_DOWN_MASK));
        menuItem.setActionCommand("Undo");
        menuItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        handleAction();
    }

    public void handleAction(){
        System.out.println(menuItem.getText());
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
