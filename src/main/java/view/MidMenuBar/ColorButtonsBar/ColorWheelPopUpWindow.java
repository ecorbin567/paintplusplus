package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;


public class ColorWheelPopUpWindow extends JDialog{

    private final ColorWheelPanel wheel;

    public ColorWheelPopUpWindow(Window window){
        super(window, "Choose new Color", ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 500);

        wheel = new ColorWheelPanel(300);
        getContentPane().add(wheel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(window);
    }
    public Color getSelectedColor(){
        return wheel.getSelectedColor();
    }

}
