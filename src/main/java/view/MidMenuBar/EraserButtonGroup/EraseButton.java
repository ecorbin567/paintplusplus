package view.MidMenuBar.EraserButtonGroup;


import entity.ToolEnum;
import use_case.toolselection.ToolSelectionInputBoundary;
import use_case.toolselection.ToolSelectionInputData;

import javax.swing.*;
import java.awt.*;

public class EraseButton {
    ToolEnum tool = ToolEnum.ERASER;
    JButton button;
    ImageIcon imageIcon;
    ToolSelectionInputBoundary inputBoundary;

    public EraseButton(ToolSelectionInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
        ErasePopUp erasePopUp = new ErasePopUp(inputBoundary);
        button = new JButton();
        imageIcon = new ImageIcon(EraseButton.class.getResource("/images/EraseIcon.png"));
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(event -> {
            ToolSelectionInputData inputData = new ToolSelectionInputData(tool);
            inputBoundary.selectTool(tool);
            inputBoundary.selectColor(Color.WHITE);
        });

        button.addActionListener(e -> {
            erasePopUp.getPopupMenu().show(button, button.getWidth(), button.getHeight());
        });
    }

    public JButton getButton() {
        return button;
    }
}

