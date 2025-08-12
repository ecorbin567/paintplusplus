package view.topmenubar.filemenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Menu item for saving the current drawing as a new file.
 */
public class SaveAsButton {
    private final JMenuItem menuItem;
    private static final Logger logger = Logger.getLogger(SaveAsButton.class.getName());
    private static final String NAME = "Save As";

    /**
     * Creates the save as menu item.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
    public SaveAsButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem(NAME);
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand(NAME);
        menuItem.addActionListener(e -> {
            BufferedImage image = drawingView.getImage();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(NAME);
            fileChooser.setSelectedFile(new File(System.getProperty("user.dir") + "/untitled.png"));
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".png")) {
                    file = new File(file.getAbsolutePath() + ".png");
                }
                controller.save(image, file);
            } else {
                logger.log(Level.SEVERE, NAME);
            }
        });
    }

    /**
     * @return the save as menu item
     */
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
