package view.topmenubar.filemenu;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

public class SaveAsButton {
    private final JMenuItem menuItem;

    public SaveAsButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Save As");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand("saveAs");
        menuItem.addActionListener(e -> {
            BufferedImage image = drawingView.getImage();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save As");
            fileChooser.setSelectedFile(new File(System.getProperty("user.dir") + "/untitled.png"));
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".png")) {
                    file = new File(file.getAbsolutePath() + ".png");
                }
                controller.save(image, file);
            }
            else {
                System.out.println("No file selected");
            }
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
