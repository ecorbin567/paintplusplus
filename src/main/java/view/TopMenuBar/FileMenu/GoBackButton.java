package view.TopMenuBar.FileMenu;

import view.TopMenuBar.MenuActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GoBackButton {
    JMenuItem menuItem;
    private MenuActionListener listener;

    public GoBackButton() {
        menuItem = new JMenuItem("Back to My Canvases");
        menuItem.setActionCommand("goBack");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));

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
