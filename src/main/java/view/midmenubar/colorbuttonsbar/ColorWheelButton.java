package view.midmenubar.colorbuttonsbar;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;
import view.midmenubar.SelectionToolButton;

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

//        colorWheelButton.addActionListener(e -> {
//            ColorWheelPopUpWindow popUpWindow =
//                    new ColorWheelPopUpWindow(
//                            SwingUtilities.getWindowAncestor(panel)
//                    );
//            popUpWindow.setVisible(true);
//
//            if (popUpWindow.isConfirmed()) {
//                Color picked = popUpWindow.getSelectedColor();
//
//                if (upperColorChooserButton.isSelected()) {
//                    primaryInteractor.changeColor(new ChangeColorInputData(picked));
//                } else {
//                    secondaryInteractor.changeColor(new ChangeColorInputData(picked));
//                }
//            }
//            colorWheelButton.setSelected(false); // after toggling wheel button, toggle button off
//        });
    }


    public JToggleButton getColorWheelButton(){
        return this;
    }
}
