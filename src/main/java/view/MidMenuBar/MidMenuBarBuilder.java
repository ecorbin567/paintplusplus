package view.MidMenuBar;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class MidMenuBarBuilder {

    JPanel panel;
    JButton eButton;
    JButton pButton;
    JButton sButton;
    JButton iButton;
    DrawingCanvas canvas;

    public MidMenuBarBuilder(DrawingCanvas canvas) {
        this.canvas = canvas;
        PencilButton pencilButton = new PencilButton(canvas);
        pButton = pencilButton.getButton();

        EraseButton eraseButton = new EraseButton(canvas);
        eButton = eraseButton.getButton();

        SelectButton selectButton = new SelectButton();
        sButton = selectButton.getButton();

        ImageButton imageButton = new ImageButton();
        iButton = imageButton.getButton();

        panel = new JPanel();
        panel.add(pButton);
        panel.add(eButton);
        panel.add(sButton);
        panel.add(iButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getPencilButton() {
        return pButton;
    }

    public JButton getEraseButton() {
        return eButton;
    }

}
