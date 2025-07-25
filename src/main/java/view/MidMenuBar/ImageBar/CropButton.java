package view.MidMenuBar.ImageBar;

import interface_adapter.image.crop.CropController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


public class CropButton {
    JButton button;

    public CropButton(CropController controller) {
        button = new JButton();
        ImageIcon icon = new ImageIcon(CropButton.class.getResource("/images/CropIcon.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    controller.execute(x, y, w, h);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage());
                }
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
