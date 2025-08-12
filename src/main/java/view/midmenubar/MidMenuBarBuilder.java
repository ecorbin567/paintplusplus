package view.MidMenuBar;

import java.util.List;

import interface_adapter.canvas.CanvasController;
import interface_adapter.changecolor.ColorController;
import interface_adapter.midmenu.image.ImageFacade;
import interface_adapter.midmenu.image.crop.CropController;
import interface_adapter.midmenu.image.import_image.ImportController;
import interface_adapter.midmenu.image.resize.ResizeController;
import interface_adapter.midmenu.image.rotate.RotateController;
import view.DrawingView;

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
    JToggleButton upperColorChooserButton;
    JToggleButton lowerColorChooserButton;
    JToggleButton colorWheelButton;
    CanvasController canvasController;
    ImportButton importButtonObject;

    public MidMenuBarBuilder(CanvasController canvasController,
                             ImageFacade imageFacade,
                             ColorController colorController,
                             DrawingView drawingView) {

        this.canvasController = canvasController;

        PencilButton pencilButton = new PencilButton(this.canvasController);
        pButton = pencilButton.getButton();

        EraseButton eraseButton = new EraseButton(this.canvasController);
        eButton = eraseButton.getButton();

        SelectionToolButton selectButton = new SelectionToolButton(canvasController);
        sButton = selectButton.getButton();

        ImportButton imageButton = new ImportButton(imageFacade, drawingView);
        iButton = imageButton.getButton();
        this.importButtonObject = imageButton; //  JOSH: Lol store it

        CropButton crop = new CropButton(imageFacade, drawingView);
        cropButton = crop.getButton();

        ResizeImageButton resize = new ResizeImageButton(imageFacade, drawingView);
        resizeButton = resize.getButton();

        RotateButton rotate = new RotateButton(imageFacade, drawingView);

        rotateButton = rotate.getButton();

        UpperColorChooserButton upperChooserButton = new UpperColorChooserButton(colorController);
        LowerColorChooserButton lowerChooserButton = new LowerColorChooserButton(colorController);
        upperColorChooserButton = upperChooserButton.getButton();
        lowerColorChooserButton = lowerChooserButton.getButton();



        // how we add to the panel on the buttons to the midmenu worry about later on when refractoring
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        ColorWheelButton colorWheel = new ColorWheelButton(panel, colorController,
                upperChooserButton, lowerChooserButton);
        colorWheelButton = colorWheel.getColorWheelButton();

        // selected tools logic to work with selectiontool code

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

        // track which chooser (upper or lower color chooser buttons) is active
        ButtonGroup chooserGroup = new ButtonGroup();
        chooserGroup.add(upperColorChooserButton);
        chooserGroup.add(lowerColorChooserButton);

        upperColorChooserButton.setSelected(true);

        // handle individual solid color buttons
        List<Color> solidColors = List.of(
                Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW,
                Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA
        );
        for (Color solidColor: solidColors) {
            SingleColorButton swatch = new SingleColorButton(solidColor, colorController,
                    upperChooserButton, lowerChooserButton);
            solidColorsPanel.add(swatch);
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

    /**
     * Getter for import button
     * @return the import button
     */
    public ImportButton getImportButtonObject() {
        return importButtonObject; // Josh: lol
    }
}
