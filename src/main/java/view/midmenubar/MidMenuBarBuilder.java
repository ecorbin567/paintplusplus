package view.midmenubar;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import interface_adapter.canvas.CanvasController;
import interface_adapter.changecolor.ColorController;
import interface_adapter.image.ImageFacade;
import view.DrawingView;
import view.midmenubar.colorbuttonsbar.ColorWheelButton;
import view.midmenubar.colorbuttonsbar.LowerColorChooserButton;
import view.midmenubar.colorbuttonsbar.SingleColorButton;
import view.midmenubar.colorbuttonsbar.UpperColorChooserButton;
import view.midmenubar.eraserbuttongroup.EraseButton;
import view.midmenubar.imagebar.CropButton;
import view.midmenubar.imagebar.ImportButton;
import view.midmenubar.imagebar.ResizeImageButton;
import view.midmenubar.imagebar.RotateButton;
import view.midmenubar.pencilbuttongroup.PencilButton;

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

        colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));// entire color panel (wheel, individual,
        // selectors)

        colorChooserPanel = new JPanel(new GridLayout(2, 1, 2, 2));
        colorChooserPanel.add(upperColorChooserButton);
        colorChooserPanel.add(lowerColorChooserButton);

        solidColorsPanel = new JPanel(new GridLayout(2, 4, 4, 4)); // panel of small solid colors

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
        for (Color solidColor : solidColors) {
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

    /**
     * Getter for import button
     *
     * @return the import button
     */
    public ImportButton getImportButtonObject() {
        return importButtonObject; // Josh: lol
    }
}
