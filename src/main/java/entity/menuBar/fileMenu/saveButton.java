package entity.menuBar.fileMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class saveButton implements ActionListener {

    JMenuItem menuItem;
    public saveButton(){
        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_DOWN_MASK));
        menuItem.setActionCommand("save");
        menuItem.addActionListener(this);
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    private static void save(String fileName) {

        File file = new File(fileName + ".png");
        BufferedImage image = getImage();
        try {
            ImageIO.write(image, "png", file);  // ignore returned boolean
        } catch(IOException e) {
            System.out.println("Write error for " + file.getPath() +
                    ": " + e.getMessage());
        }
    }

    public void handleAction(){
        if (true){
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select a folder to save to");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            int result = chooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDir = chooser.getSelectedFile();

                // Step 2: Construct file path and call save()
                String fileName = new File(selectedDir, "my_image").getAbsolutePath(); // no extension yet
                save(fileName); // save as my_image.png

        }

        else{
            System.out.println("No file selected");
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
