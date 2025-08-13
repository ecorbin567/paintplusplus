package view.midmenubar.eraserbuttongroup;


import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class EraseButton {
    private static final ToolEnum toolName = ToolEnum.ERASER;
    private final JButton button;
    ImageIcon imageIcon;

    public EraseButton(CanvasController canvasController) {
        ErasePopUp erasePopUp = new ErasePopUp(canvasController);
        button = new JButton();
        URL url = Objects.requireNonNull(
                EraseButton.class.getResource("/images/EraseIcon.png"),
                "Missing resource: /images/EraseIcon.png"
        );
        imageIcon = new ImageIcon(url);
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));

        button.addActionListener(event -> canvasController.handleTools(toolName));

         button.addActionListener(e -> erasePopUp.getPopupMenu().show(button, button.getWidth(), button.getHeight()));
    }

    public JButton getButton() {
        return button;
    }
}

