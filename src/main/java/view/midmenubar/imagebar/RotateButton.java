package view.midmenubar.imagebar;

import java.net.URL;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import interface_adapter.image.ImageFacade;
import view.DrawingView;

public class RotateButton {

    private final JButton button;

    public RotateButton(ImageFacade controller, DrawingView drawingView) {
        button = new JButton();
        final URL url = Objects.requireNonNull(
                RotateButton.class.getResource("/images/RotateIcon.png"),
                "Missing resource: /images/RotateIcon.png"
        );
        final ImageIcon icon = new ImageIcon(url);
        final java.awt.Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new java.awt.Dimension(60, 60));

        button.addActionListener(e -> {
            final String input = JOptionPane.showInputDialog(null,
                    "Enter degrees to rotate (e.g., 90, -45):",
                    "Rotate Image",
                    JOptionPane.PLAIN_MESSAGE);
            if (input != null) {
                try {
                    final double degrees = Double.parseDouble(input);
                    controller.rotate(degrees);
                    drawingView.repaint();
                }
                catch (NumberFormatException ex) {
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
