package view.topmenubar.filemenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * For saving to My Canvases
 */
public class SaveOnlineButton {
    private final JMenuItem menuItem;

    public SaveOnlineButton(DrawingView drawingView, TopMenuFacade controller) {

        menuItem = new JMenuItem("Save Online");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand("saveOnline");
        menuItem.addActionListener(e->{
            BufferedImage image = drawingView.getImage();
            String currentUserUsername = drawingView.getUsername();

            // Save to user thing in database
            controller.saveOnline(image, currentUserUsername);
        });
    }
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
