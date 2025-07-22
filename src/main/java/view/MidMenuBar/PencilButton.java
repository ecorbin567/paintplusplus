package view.MidMenuBar;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class PencilButton{
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
            button.addActionListener(evt -> canvas.paint());
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
}
