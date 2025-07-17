package entity.TopMenuBar.fileMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveAsButton implements ActionListener {

    JMenuItem menuItem;
    public SaveAsButton() {
        menuItem = new JMenuItem("Save As");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.ALT_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        menuItem.setActionCommand("saveAs");
        menuItem.addActionListener(this);

    }
    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public void handleAction() {
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
                ImageIO.write(getImage(), "png", file);
                System.out.println("Image saved to: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Write error for " + file.getPath());
                e.printStackTrace();
            }
        }
    }




    public void actionPerformed(ActionEvent e) {
        handleAction();
    }

    private static BufferedImage getImage() {
        BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g = img.createGraphics();
        g.fillRect(0, 0, 200, 200); // solid color
        g.dispose();
        return img;
    }
}
