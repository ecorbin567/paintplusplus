package view.midmenubar.colorbuttonsbar;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;
import view.midmenubar.SelectionToolButton;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class ColorWheelButton extends CircularButton{
    ImageIcon imageIcon;
    private static final ToolEnum toolName = ToolEnum.PENCIL;

    public ColorWheelButton(JPanel panel, ColorController colorController,
                            UpperColorChooserButton upperColorChooserButton,
                            LowerColorChooserButton lowerColorChooserButton) {
        super(36);

        URL url = Objects.requireNonNull(
                SelectionToolButton.class.getResource("/images/wheel.png"),
                "Missing resource: /images/wheel.png"
        );

        imageIcon = new ImageIcon(url);


        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(36, 36, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);
        this.addActionListener(e -> {
            ColorWheelPopUpWindow popUpWIndow = new ColorWheelPopUpWindow(
                    SwingUtilities.getWindowAncestor(panel));

            popUpWIndow.setVisible(true);

            if (popUpWIndow.isConfirmed()) {
                Color picked = popUpWIndow.getSelectedColor();
                colorController.handleColorChangeButton(toolName, picked);
                if (upperColorChooserButton.isSelected()) {
                    upperColorChooserButton.setCurrentColor(picked);
                }
                else{
                    lowerColorChooserButton.setCurrentColor(picked);
                }
            }
            this.setSelected(false);
        });
    }


    public JToggleButton getColorWheelButton(){
        return this;
    }
}
