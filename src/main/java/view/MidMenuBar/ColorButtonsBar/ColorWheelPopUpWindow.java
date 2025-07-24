package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;


public class ColorWheelPopUpWindow extends JDialog{

    public ColorWheelPopUpWindow(JFrame frame){
        super(frame, "Choose new Color", true);
        setSize(300, 500);
        setLayout(new FlowLayout());

        JFrame colorWheelFrame = new JFrame("ColorWheel Frame");
        ColorWheelPanel colorWheel = new ColorWheelPanel(300);
        colorWheelFrame.add(colorWheel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(frame);
        frame.setVisible(true);


    }










}
