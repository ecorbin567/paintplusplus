package view.MidMenuBar;

import entity.DrawingCanvas;
import interface_adapter.image.import_image.ImportController;
import interface_adapter.image.import_image.ImportPresenter;
import use_case.image.import_image.*;
import data_access.LocalImageLoader;
import view.MidMenuBar.EraserButtonGroup.EraseButton;
import view.MidMenuBar.ImageBar.CropButton;
import view.MidMenuBar.ImageBar.ImportButton;
import view.MidMenuBar.ImageBar.ResizeImageButton;
import view.MidMenuBar.ImageBar.RotateButton;
import view.MidMenuBar.PencilButtonGroup.PencilButton;

import javax.swing.*;

public class MidMenuBarBuilder {

    JPanel panel;
    JButton eButton;
    JButton pButton;
    JButton sButton;
    JButton iButton;
    JButton cropButton;
    JButton resizeButton;
    JButton rotateButton;
    DrawingCanvas canvas;

    public MidMenuBarBuilder(DrawingCanvas canvas) {
        this.canvas = canvas;

        ImportOutputBoundary presenter = new ImportPresenter(canvas);
        ImportGateway gateway = new LocalImageLoader();
        ImportInputBoundary interactor = new ImportInteractor(gateway, presenter);
        ImportController importController = new ImportController(interactor);

        PencilButton pencilButton = new PencilButton(canvas);
        pButton = pencilButton.getButton();

        EraseButton eraseButton = new EraseButton(canvas);
        eButton = eraseButton.getButton();

        SelectButton selectButton = new SelectButton();
        sButton = selectButton.getButton();

        ImportButton imageButton = new ImportButton(importController);
        iButton = imageButton.getButton();

        CropButton crop = new CropButton();
        cropButton = crop.getButton();

        ResizeImageButton resize = new ResizeImageButton(canvas);
        resizeButton = resize.getButton();

        RotateButton rotate = new RotateButton();
        rotateButton = rotate.getButton();

        panel = new JPanel();
        panel.add(pButton);
        panel.add(eButton);
        panel.add(sButton);
        panel.add(iButton);
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
