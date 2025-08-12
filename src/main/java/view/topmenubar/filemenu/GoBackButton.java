package view.topmenubar.filemenu;

import view.topmenubar.MenuActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Menu item for navigating back to the user's canvases.
 */
public class GoBackButton {
    JMenuItem menuItem;
    private MenuActionListener listener;

    /**
     * Creates the go back menu item.
     */
    public GoBackButton() {
        menuItem = new JMenuItem("Back to My Canvases");
        menuItem.setActionCommand("goBack");
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));

        menuItem.addActionListener((ActionEvent e) -> {
            if (listener != null) {
                listener.onMenuItemSelected(menuItem.getActionCommand());
            }
        });
    }

    /**
     * @return the go back menu item
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
