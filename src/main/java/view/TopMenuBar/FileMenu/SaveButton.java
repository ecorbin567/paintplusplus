package view.TopMenuBar.FileMenu;

import entity.DrawingCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveButton implements ActionListener {

    JMenuItem menuItem;
    DrawingCanvas canvas;
    public SaveButton(DrawingCanvas drawingCanvas) {
        canvas = drawingCanvas;
        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("save");
        menuItem.addActionListener(this);
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public void handleAction(BufferedImage image) {
        if (true) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save Image As");
            chooser.setSelectedFile(new File(System.getProperty("user.home") + "/untitled.png")); // default location: Desktop
            int result = chooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                // Append .png if not present
                if (!file.getName().toLowerCase().endsWith(".png")) {
                    file = new File(file.getAbsolutePath() + ".png");
                }
                // Replace with your actual image
                try {
                    ImageIO.write(image, "png", file);
                    System.out.println("Image saved to: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Write error for " + file.getPath());
                    e.printStackTrace();
                }
            } else {
                System.out.println("No file selected");
            }
        }
    }
public void actionPerformed(ActionEvent e) {
    handleAction(getImage());
}

private BufferedImage getImage() {
        return canvas.getImage();
}

}

