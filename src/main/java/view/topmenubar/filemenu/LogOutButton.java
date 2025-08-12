package view.topmenubar.filemenu;

import view.topmenubar.MenuActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Menu item for logging out of the application.
 */
public class LogOutButton {
    JMenuItem menuItem;
    private MenuActionListener listener;

    /**
     * Creates the log out menu item.
     */
    public LogOutButton() {
        menuItem = new JMenuItem("Log Out");
        menuItem.setActionCommand("logOut");
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));

        menuItem.addActionListener((ActionEvent e) -> {
            if (listener != null) {
                listener.onMenuItemSelected(menuItem.getActionCommand());
            }
        });
    }

    /**
     * @return the log out menu item
     */
    public JMenuItem getMenuItem() {
        return menuItem;
    }

    /**
     * Sets the menu action listener.
     *
     * @param listener the menu action listener
     */
    public void setMenuActionListener(MenuActionListener listener) {
        this.listener = listener;
    }
}
