package view.topmenubar.filemenu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import view.topmenubar.MenuActionListener;

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
