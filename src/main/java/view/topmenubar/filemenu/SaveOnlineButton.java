package view.topmenubar.filemenu;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

/**
 * For saving to My Canvases.
 */
public class SaveOnlineButton {
    private final JMenuItem menuItem;

    public SaveOnlineButton(DrawingView drawingView, TopMenuFacade controller) {

        menuItem = new JMenuItem("Save Online");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand("saveOnline");
        menuItem.addActionListener(evt -> {
            final BufferedImage image = drawingView.getImage();
            final String currentUserUsername = drawingView.getUsername();

            // Save to user thing in database
            controller.saveOnline(image, currentUserUsername);
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
