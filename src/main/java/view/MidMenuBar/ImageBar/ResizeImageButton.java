package view.MidMenuBar.ImageBar;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResizeImageButton {
    JButton button;

    public ResizeImageButton(DrawingCanvas canvas) {
        button = new JButton();
        ImageIcon icon = new ImageIcon(ResizeImageButton.class.getResource("/images/ResizeIcon.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String widthStr = JOptionPane.showInputDialog("Enter new width:");
                String heightStr = JOptionPane.showInputDialog("Enter new height:");

                if (widthStr != null && heightStr != null) {
                    try {
                        int newWidth = Integer.parseInt(widthStr);
                        int newHeight = Integer.parseInt(heightStr);

                        canvas.resizeLastImportedImage(newWidth, newHeight);
                        canvas.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                    }
                }
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
