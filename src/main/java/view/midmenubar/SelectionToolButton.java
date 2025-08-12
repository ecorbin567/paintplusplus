package view.midmenubar;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.*;

public class SelectionToolButton {
    ToolEnum toolName = ToolEnum.SELECT;
    JButton button;
    public SelectionToolButton(CanvasController canvasController) {
        button = new JButton();
        ImageIcon icon = new ImageIcon(SelectionToolButton.class.getResource("/images/SelectIcon.png"));
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(60, 60));
        button.addActionListener(e -> canvasController.handleSelectButtonPress(toolName));
    }

    public JButton getButton() {
        return button;
    }
}
