package view.MidMenuBar.PencilButtonGroup;

import entity.ToolEnum;
import use_case.toolselection.ToolSelectionInputBoundary;
import use_case.toolselection.ToolSelectionInputData;

import javax.swing.*;
import java.awt.*;

public class PencilButton{
    //ImageIcon is for Images of the PencilButton
    ToolEnum tool = ToolEnum.PENCIL;
    JButton button;
    ImageIcon imageIcon;
    ToolSelectionInputBoundary inputBoundary;

    public PencilButton (ToolSelectionInputBoundary inputBoundary) {
        button = new JButton();
        this.inputBoundary = inputBoundary;
        PencilPopUp pencilPopUp = new PencilPopUp(inputBoundary);
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
            ToolSelectionInputData inputData = new ToolSelectionInputData(tool);
            inputBoundary.selectTool(tool);
            inputBoundary.selectColor(Color.BLACK);
        });

        button.addActionListener(e -> {
            pencilPopUp.getPopupMenu().show(button, button.getWidth(), button.getHeight());
        });

    }

    public JButton getButton() {
        return button;
    }
}
