package view.midmenubar.pencilbuttongroup;

import entity.ToolEnum;

import interface_adapter.canvas.CanvasController;
import view.midmenubar.eraserbuttongroup.EraseButton;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class PencilButton{
    //ImageIcon is for Images of the PencilButton
    private static final ToolEnum tool = ToolEnum.PENCIL;
    private final JButton button;

    public PencilButton (CanvasController canvasController) {
        button = new JButton();
        PencilPopUp pencilPopUp = new PencilPopUp(canvasController);
        URL url = Objects.requireNonNull(
                EraseButton.class.getResource("/images/PencilIcon.png"),
                "Missing resource: /images/PencilIcon.png"
        );
        ImageIcon imageIcon = new ImageIcon(url);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));
        button.addActionListener(event -> canvasController.handleTools(tool));

        button.addActionListener(e -> pencilPopUp.getPopupMenu().show(button,
                button.getWidth(), button.getHeight()));
    }

    public JButton getButton() {
        return button;
    }
}
