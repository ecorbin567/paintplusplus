package entity.TopMenuBar.editMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class RedoButton implements ActionListener {
    JMenuItem menuItem;

    public RedoButton(){
        menuItem = new JMenuItem("Redo");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                KeyEvent.SHIFT_DOWN_MASK | KeyEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(this);
        menuItem.setActionCommand("redo");
    }

    public void actionPerformed(ActionEvent e) {
        handleAction();
    }

    public void handleAction() {
        System.out.println(menuItem.getText());
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
