package view.midmenubar;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class SelectionToolButton {
    ToolEnum toolName = ToolEnum.SELECT;
    JButton button;
    public SelectionToolButton(CanvasController canvasController) {
        button = new JButton();
        URL url = Objects.requireNonNull(
                SelectionToolButton.class.getResource("/images/wheel.png"),
                "Missing resource: /images/wheel.png"
        );
        ImageIcon icon = new ImageIcon(url);
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
