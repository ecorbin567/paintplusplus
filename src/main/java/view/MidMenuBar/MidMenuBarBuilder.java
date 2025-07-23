package view.MidMenuBar;

import entity.DrawingCanvas;
import view.MidMenuBar.ImageBar.CropButton;
import view.MidMenuBar.ImageBar.ImportButton;
import view.MidMenuBar.ImageBar.ResizeImageButton;
import view.MidMenuBar.ImageBar.RotateButton;

import javax.swing.*;

public class MidMenuBarBuilder {

    JPanel panel;
    JButton eButton;
    JButton pButton;
    JButton sButton;
    JButton importButton;
    JButton cropButton;
    JButton resizeButton;
    JButton rotateButton;
    DrawingCanvas canvas;

    public MidMenuBarBuilder(DrawingCanvas canvas) {
        this.canvas = canvas;
        PencilButton pencilButton = new PencilButton(canvas);
        pButton = pencilButton.getButton();

        EraseButton eraseButton = new EraseButton(canvas);
        eButton = eraseButton.getButton();

        SelectButton selectButton = new SelectButton();
        sButton = selectButton.getButton();

        ImportButton imageButton = new ImportButton();
        importButton = imageButton.getButton();

        CropButton crop = new CropButton();
        cropButton = crop.getButton();

        ResizeImageButton resize = new ResizeImageButton();
        resizeButton = resize.getButton();

        RotateButton rotate = new RotateButton();
        rotateButton = rotate.getButton();

        panel = new JPanel();
        panel.add(pButton);
        panel.add(eButton);
        panel.add(sButton);
        panel.add(importButton);
        panel.add(cropButton);
        panel.add(resizeButton);
        panel.add(rotateButton);
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
