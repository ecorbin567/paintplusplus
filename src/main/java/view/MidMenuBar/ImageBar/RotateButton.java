package view.MidMenuBar.ImageBar;

import interface_adapter.image.rotate.RotateController;
import view.CanvasView;

import javax.swing.*;

public class RotateButton {

    private final JButton button;

    public RotateButton(RotateController rotateController, CanvasView canvasView) {
        button = new JButton();
        ImageIcon icon = new ImageIcon(RotateButton.class.getResource("/images/RotateIcon.png"));
        java.awt.Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new java.awt.Dimension(60, 60));

        button.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(null,
                    "Enter degrees to rotate (e.g., 90, -45):",
                    "Rotate Image",
                    JOptionPane.PLAIN_MESSAGE);
            if (input != null) {
                try {
                    double degrees = Double.parseDouble(input);
                    rotateController.execute(degrees);
                    canvasView.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid number.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
