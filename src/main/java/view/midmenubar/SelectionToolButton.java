package view.midmenubar;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

public class SelectionToolButton {
    ToolEnum toolName = ToolEnum.SELECT;
    JButton button;

    public SelectionToolButton(CanvasController canvasController) {
        button = new JButton();
        final URL url = Objects.requireNonNull(
                SelectionToolButton.class.getResource("/images/SelectIcon.png"),
                "Missing resource: /images/SelectIcon.png"
        );
        ImageIcon icon = new ImageIcon(url);
        final Image image = icon.getImage();
        final Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(60, 60));
        button.addActionListener(e -> canvasController.handleTools(toolName));
    }

    public JButton getButton() {
        return button;
    }
}
