package view.TopMenuBar.FileMenu;

import view.TopMenuBar.MenuActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class LogOutButton {
    JMenuItem menuItem;
    private MenuActionListener listener;

    public LogOutButton() {
        menuItem = new JMenuItem("Log Out");
        menuItem.setActionCommand("logOut");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));

        menuItem.addActionListener((ActionEvent e) -> {
            if (listener != null) {
                listener.onMenuItemSelected(menuItem.getActionCommand());
            }
        });
    }
    public JMenuItem getMenuItem() {
        return menuItem;
    }

    // Setter to allow higher-level classes to inject the listener
    public void setMenuActionListener(MenuActionListener listener) {
        this.listener = listener;
    }
}
