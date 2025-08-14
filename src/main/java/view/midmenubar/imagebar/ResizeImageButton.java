package view.midmenubar.imagebar;

import java.net.URL;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.image.ImageFacade;
import view.DrawingView;

public class ResizeImageButton {
    private final JButton button;

    public ResizeImageButton(ImageFacade controller, DrawingView drawingView) {
        button = new JButton();
        final URL url = Objects.requireNonNull(
                ResizeImageButton.class.getResource("/images/ResizeIcon.png"),
                "Missing resource: /images/ResizeIcon.png"
        );
        final ImageIcon icon = new ImageIcon(url);
        final java.awt.Image image = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setPreferredSize(new java.awt.Dimension(60, 60));

        button.addActionListener(e -> {
            final JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Width:"));
            final JTextField widthField = new JTextField(5);
            myPanel.add(widthField);
            myPanel.add(Box.createHorizontalStrut(15));
            myPanel.add(new JLabel("Height:"));
            final JTextField heightField = new JTextField(5);
            myPanel.add(heightField);

            final int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Please Enter New Dimensions", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    final int newWidth = Integer.parseInt(widthField.getText());
                    final int newHeight = Integer.parseInt(heightField.getText());
                    if (newWidth <= 0 || newHeight <= 0) {
                        throw new NumberFormatException();
                    }
                    controller.resize(newWidth, newHeight);
                    drawingView.repaint();
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid positive integers.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}
