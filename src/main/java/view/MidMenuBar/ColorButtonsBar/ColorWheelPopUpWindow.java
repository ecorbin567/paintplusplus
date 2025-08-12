package view.midmenubar.colorbuttonsbar;

import jdk.jfr.FlightRecorderListener;

import javax.swing.*;
import java.awt.*;


public class ColorWheelPopUpWindow extends JDialog{

    private final ColorWheelPanel wheel;
    private boolean confirmed = false;

    public ColorWheelPopUpWindow(Window window){
        super(window, "Choose new Color", ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout(5, 5));

        wheel = new ColorWheelPanel(300);
        getContentPane().add(wheel, BorderLayout.CENTER);
        // button row
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        ok.addActionListener(e -> {
            confirmed = true;
            dispose();
        });
        cancel.addActionListener(e -> {
            dispose();
        });

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        buttons.add(ok);
        buttons.add(cancel);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(window);
    }

    public boolean isConfirmed(){
        return this.confirmed;
    }

    public Color getSelectedColor(){
        return wheel.getSelectedColor();
    }

}
