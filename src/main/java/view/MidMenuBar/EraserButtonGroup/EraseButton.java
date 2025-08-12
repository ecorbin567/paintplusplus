package view.midmenubar.eraserbuttongroup;


import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.*;

public class EraseButton {
    private final ToolEnum toolName = ToolEnum.ERASER;
    private final JButton button;
    ImageIcon imageIcon;

    public EraseButton(CanvasController canvasController) {
        ErasePopUp erasePopUp = new ErasePopUp(canvasController);
        button = new JButton();
        imageIcon = new ImageIcon(EraseButton.class.getResource("/images/EraseIcon.png"));
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(event -> {
            canvasController.handleEraserButtonPress(toolName);
        });

         button.addActionListener(e -> {
            erasePopUp.getPopupMenu().show(button, button.getWidth(), button.getHeight());
        });
    }

    public JButton getButton() {
        return button;
    }
}

