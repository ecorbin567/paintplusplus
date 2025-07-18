package view.MidMenuBar;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PencilButton implements ActionListener{
    JButton button;
    ImageIcon imageIcon;
    DrawingCanvas canvas;

    public PencilButton(DrawingCanvas drawingCanvas) {
        canvas = drawingCanvas;
        button = new JButton();
        try{
            imageIcon = new ImageIcon(PencilButton.class.getResource("/images/PencilIcon.png"));
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImage);
        }
        catch( Exception e ){
            e.printStackTrace();
        }
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));


    }

    public JButton getButton() {
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        button.addActionListener(evt -> canvas.paint());
    }


}
