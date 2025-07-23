package view.MidMenuBar.PencilButtonGroup;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class PencilButton{
    //ImageIcon is for Images of the PencilButton
    JButton button;
    ImageIcon imageIcon;
    DrawingCanvas canvas;

    public PencilButton(DrawingCanvas drawingCanvas) {
        canvas = drawingCanvas;
        button = new JButton();
        PencilPopUp pencilPopUp = new PencilPopUp(drawingCanvas);
        try{
            imageIcon = new ImageIcon(PencilButton.class.getResource("/images/PencilIcon.png"));
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImage);
            button.addActionListener(evt -> canvas.paint());
            button.addActionListener(e -> {
                pencilPopUp.getPopupMenu().show(button, button.getWidth(), button.getHeight());
            });


        }
        catch( Exception e ){
            e.printStackTrace();
            System.out.println("Make sure resources folder is set to default resources folder.");
            System.out.println("You can do that but right clicking on resources and going to mark directory as");
        }
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));
    }

    public JButton getButton() {
        return button;
    }
}
