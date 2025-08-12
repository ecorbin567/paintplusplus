package view.TopMenuBar.FileMenu;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * For saving to My Canvases
 */
public class SaveOnlineButton {
    private static final ToolEnum toolName = ToolEnum.SAVE;
    private final JMenuItem menuItem;


    public SaveOnlineButton(DrawingView drawingView, CanvasController canvasController) {

        menuItem = new JMenuItem("Save Online");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand("saveOnline");
        menuItem.addActionListener(e->{
            BufferedImage image = drawingView.getImage();
            String currentUserUsername = drawingView.getUsername();

            // Save to user thing in database
            canvasController.handleSaveOnlineButtonPress(image, currentUserUsername);
        });
    }
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
