package view.midmenubar.imagebar;

import interface_adapter.image.ImageFacade;
import view.DrawingView;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

public class ResizeImageButton {
    private final JButton button;

    public ResizeImageButton(ImageFacade controller, DrawingView drawingView) {
        button = new JButton();
        URL url = Objects.requireNonNull(
                ResizeImageButton.class.getResource("/images/wheel.png"),
                "Missing resource: /images/wheel.png"
        );
        ImageIcon icon = new ImageIcon(url);
        java.awt.Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new java.awt.Dimension(60, 60));

        button.addActionListener(e -> {
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Width:"));
            JTextField widthField = new JTextField(5);
            myPanel.add(widthField);
            myPanel.add(Box.createHorizontalStrut(15));
            myPanel.add(new JLabel("Height:"));
            JTextField heightField = new JTextField(5);
            myPanel.add(heightField);

            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please Enter New Dimensions", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    int newWidth = Integer.parseInt(widthField.getText());
                    int newHeight = Integer.parseInt(heightField.getText());
                    if (newWidth <= 0 || newHeight <= 0) {
                        throw new NumberFormatException();
                    }
                    controller.resize(newWidth, newHeight);
                    drawingView.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid positive integers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
