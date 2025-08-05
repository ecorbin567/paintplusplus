package view.MidMenuBar.PencilButtonGroup;

import entity.ToolEnum;

import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.*;

public class PencilButton{
    //ImageIcon is for Images of the PencilButton
    private final ToolEnum tool = ToolEnum.PENCIL;
    private final JButton button;
    private ImageIcon imageIcon;
    private final CanvasController canvasController;

    public PencilButton (CanvasController canvasController) {
        button = new JButton();
        this.canvasController = canvasController;
        PencilPopUp pencilPopUp = new PencilPopUp(this.canvasController);
        try{
            imageIcon = new ImageIcon(PencilButton.class.getResource("/images/PencilIcon.png"));
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newImage);
            button.setIcon(imageIcon);
            button.setPreferredSize(new Dimension(60, 60));
        }
        catch( Exception e ){
            e.printStackTrace();
        }

        button.addActionListener(event -> {
            this.canvasController.handlePencilButtonPress(tool);
        });

        button.addActionListener(e -> pencilPopUp.getPopupMenu().show(button,
                button.getWidth(), button.getHeight()));

    }

    public JButton getButton() {
        return button;
    }
}
