package view.MidMenuBar;

import java.util.List;
import entity.DrawingCanvas;
import interface_adapter.image.crop.*;
import use_case.image.crop.*;
import interface_adapter.image.import_image.*;
import use_case.image.import_image.*;
import data_access.LocalImageLoader;
import view.MidMenuBar.ColorButtonsBar.*;
import view.MidMenuBar.EraserButtonGroup.EraseButton;
import view.MidMenuBar.ImageBar.CropButton;
import view.MidMenuBar.ImageBar.ImportButton;
import view.MidMenuBar.ImageBar.ResizeImageButton;
import view.MidMenuBar.ImageBar.RotateButton;
import view.MidMenuBar.PencilButtonGroup.PencilButton;

import javax.swing.*;
import java.awt.*;

public class MidMenuBarBuilder {

    JPanel panel;
    JPanel solidColorsPanel;
    JPanel colorChooserPanel;
    JPanel colorPanel;
    JButton eButton;
    JButton pButton;
    JButton sButton;
    JButton iButton;
    JButton cropButton;
    JButton resizeButton;
    JButton rotateButton;
    JButton upperColorChooserButton;
    JButton lowerColorChooserButton;
    JButton colorWheelButton;
    DrawingCanvas canvas;

    public MidMenuBarBuilder(DrawingCanvas canvas) {
        this.canvas = canvas;

        CropOutputBoundary cropPresenter = new CropPresenter(canvas);
        CropInputBoundary cropInteractor = new CropInteractor(canvas, cropPresenter);
        CropController cropController = new CropController(cropInteractor);

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

        CropButton crop = new CropButton(cropController);
        cropButton = crop.getButton();

        ResizeImageButton resize = new ResizeImageButton(canvas);
        resizeButton = resize.getButton();

        RotateButton rotate = new RotateButton(canvas);
        rotateButton = rotate.getButton();

        UpperColorChooserButton upperChooserButton = new UpperColorChooserButton();
        LowerColorChooserButton lowerChooserButton = new LowerColorChooserButton();
        upperColorChooserButton = upperChooserButton.getButton();
        lowerColorChooserButton = lowerChooserButton.getButton();

        ColorWheelButton colorWheel = new ColorWheelButton();
        colorWheelButton = colorWheel.getColorWheelButton();

        // how we add to the panel on the buttons to the midmenu worry about later on when refractoring
        panel = new JPanel();
        panel.add(pButton);
        panel.add(eButton);
        panel.add(sButton);
        panel.add(iButton);
        panel.add(cropButton);
        panel.add(resizeButton);
        panel.add(rotateButton);

        colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));// entire color panel (wheel, individual, selectors)

        colorChooserPanel = new JPanel(new GridLayout(2,1,2,2));
        colorChooserPanel.add(upperColorChooserButton);
        colorChooserPanel.add(lowerColorChooserButton);

        solidColorsPanel = new JPanel(new GridLayout(2, 4,4,4)); // panel of small solid colors

        // logic for color choosing driver code below
        final boolean[] isUpperActive = {true};

        upperChooserButton.addActionListener( e -> isUpperActive[0] = true);
        lowerChooserButton.addActionListener(e -> isUpperActive[0] = false);

        colorWheelButton.addActionListener(e -> {
            ColorWheelPopUpWindow popUpWindow =
                    new ColorWheelPopUpWindow(
                            SwingUtilities.getWindowAncestor(panel)
                    );
            popUpWindow.setVisible(true);
            Color picked = popUpWindow.getSelectedColor();

            if (isUpperActive[0]) {
                upperChooserButton.setCurrentColor(picked);
            } else {
                lowerChooserButton.setCurrentColor(picked);
            }
        });
        // handle individual solid color buttons
        List<Color> solidColors = List.of(
                Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA
        );
        for (Color solidColor: solidColors) {
            SingleColorButton swatch = new SingleColorButton(solidColor);
            solidColorsPanel.add(swatch);
            swatch.addActionListener(e -> {
                if (isUpperActive[0]) {
                    upperChooserButton.setCurrentColor(solidColor);
                } else {
                    lowerChooserButton.setCurrentColor(solidColor);
                }
            });
        }
        colorPanel.add(colorChooserPanel);
        colorPanel.add(solidColorsPanel);
        colorPanel.add(colorWheelButton);

        panel.add(colorPanel);



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
