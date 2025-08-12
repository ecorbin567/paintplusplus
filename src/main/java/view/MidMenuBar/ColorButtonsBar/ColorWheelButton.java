package view.MidMenuBar.ColorButtonsBar;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;
import view.MidMenuBar.SelectionToolButton;

import javax.swing.*;
import java.awt.*;

public class ColorWheelButton extends CircularButton{
    ImageIcon imageIcon;
    private static final ToolEnum toolName = ToolEnum.PENCIL;

    public ColorWheelButton(JPanel panel, ColorController colorController,
                            UpperColorChooserButton upperColorChooserButton,
                            LowerColorChooserButton lowerColorChooserButton) {
        super(36);
        imageIcon = new ImageIcon(
                SelectionToolButton.class.getResource("/images/wheel.png"));

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
