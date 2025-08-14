package view.midmenubar.colorbuttonsbar;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;
import view.midmenubar.SelectionToolButton;

public class ColorWheelButton extends CircularButton {
    ImageIcon imageIcon;
    private static final ToolEnum toolName = ToolEnum.PENCIL;

    public ColorWheelButton(JPanel panel, ColorController colorController,
                            UpperColorChooserButton upperColorChooserButton,
                            LowerColorChooserButton lowerColorChooserButton) {
        super(36);

        final URL url = Objects.requireNonNull(
                SelectionToolButton.class.getResource("/images/wheel.png"),
                "Missing resource: /images/wheel.png"
        );

        imageIcon = new ImageIcon(url);


        final Image image = imageIcon.getImage();
        final Image newImage = image.getScaledInstance(36, 36, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);
        this.addActionListener(e -> {
            final ColorWheelPopUpWindow popUpWIndow = new ColorWheelPopUpWindow(
                    SwingUtilities.getWindowAncestor(panel));

            popUpWIndow.setVisible(true);

            if (popUpWIndow.isConfirmed()) {
                final Color picked = popUpWIndow.getSelectedColor();
                colorController.handleColorChangeButton(toolName, picked);
                if (upperColorChooserButton.isSelected()) {
                    upperColorChooserButton.setCurrentColor(picked);
                }
                else {
                    lowerColorChooserButton.setCurrentColor(picked);
                }
            }
            this.setSelected(false);
        });
    }

    public JToggleButton getColorWheelButton() {
        return this;
    }
}
