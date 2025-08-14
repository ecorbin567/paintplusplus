package view.midmenubar.imagebar;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import interface_adapter.image.ImageFacade;
import view.DrawingView;

public class CropButton {
    JButton button;

    public CropButton(ImageFacade imageFacade, DrawingView drawingView) {
        button = new JButton();
        URL url = Objects.requireNonNull(
                CropButton.class.getResource("/images/CropIcon.png"),
                "Missing resource: /images/CropIcon.png"
        );
        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(e -> {
            // Prompt user input
            String xStr = JOptionPane.showInputDialog("Enter crop X:");
            String yStr = JOptionPane.showInputDialog("Enter crop Y:");
            String wStr = JOptionPane.showInputDialog("Enter crop Width:");
            String hStr = JOptionPane.showInputDialog("Enter crop Height:");

            try {
                int x = Integer.parseInt(xStr);
                int y = Integer.parseInt(yStr);
                int w = Integer.parseInt(wStr);
                int h = Integer.parseInt(hStr);

                imageFacade.crop(x, y, w, h);
                drawingView.repaint();


            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage());
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
