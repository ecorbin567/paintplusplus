package view.MidMenuBar;

import java.util.List;

import entity.DrawingCanvas;
import entity.ActionHistory;
import entity.Paintbrush;
import interface_adapter.image.crop.*;
import use_case.changecolor.*;
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
    JToggleButton upperColorChooserButton;
    JToggleButton lowerColorChooserButton;
    JToggleButton colorWheelButton;
    DrawingCanvas canvas;

    public MidMenuBarBuilder(DrawingCanvas canvas) {
        this.canvas = canvas;
        Paintbrush brush = canvas.getPaintbrush();

        CropOutputBoundary cropPresenter = new CropPresenter(canvas);
        ActionHistory actionHistory = canvas.getActionHistory();
        CropInputBoundary cropInteractor = new CropInteractor(canvas, cropPresenter, actionHistory);
        CropController cropController = new CropController(cropInteractor);

        ImportOutputBoundary presenter = new ImportPresenter(canvas);
        ImportGateway gateway = new LocalImageLoader();
        ImportInputBoundary interactor = new ImportInteractor(gateway, presenter, actionHistory);
        ImportController importController = new ImportController(interactor);

        PencilButton pencilButton = new PencilButton();
        pButton = pencilButton.getButton();

        EraseButton eraseButton = new EraseButton();
        eButton = eraseButton.getButton();

        SelectionToolButton selectButton = new SelectionToolButton();
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
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // selected tools logic to work with selectiontool code
        pButton.addActionListener(e -> {
            canvas.setSelectedTool("PaintBrush");
            canvas.getSelectionTool().cancel();
            canvas.repaint();
        });
        eButton.addActionListener(e -> {
            canvas.setSelectedTool("Eraser");
            canvas.getSelectionTool().cancel();
            canvas.repaint();
        });
        sButton.addActionListener( e -> {
            canvas.setSelectedTool("Selection");
            // get rid of any older rectangle
            canvas.getSelectionTool().cancel();
            canvas.repaint();
        });

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
        ChangeColorOutputBoundary primaryPresenter = new ChangeColorPresenter(
                brush, upperChooserButton, lowerChooserButton, true); // presenter driver of domain and model
        ChangeColorInputBoundary primaryInteractor = new ChangeColorInteractor(brush, primaryPresenter); // check code below each time we change color for colorwheel or swatch

        ChangeColorOutputBoundary secondaryPresenter = new ChangeColorPresenter(
                brush, upperChooserButton, lowerChooserButton, false);
        ChangeColorInputBoundary secondaryInteractor = new ChangeColorInteractor(brush, secondaryPresenter);

        // track which chooser (upper or lower color chooser buttons) is active
        ButtonGroup chooserGroup = new ButtonGroup();
        chooserGroup.add(upperColorChooserButton);
        chooserGroup.add(lowerColorChooserButton);

        upperColorChooserButton.setSelected(true);

        upperChooserButton.addActionListener(e -> {
            // immediately set brush to whatever color the upper chooser is showing:
            primaryInteractor.changeColor(
                    new ChangeColorInputData(
                            upperChooserButton.getUpperCurrentColor()
                    )
            );
        });

        lowerChooserButton.addActionListener(e -> {
            // immediately set brush to whatever color the lower chooser is showing:
            secondaryInteractor.changeColor(
                    new ChangeColorInputData(
                            lowerChooserButton.getLowerCurrentColor()
                    )
            );
        });

        // color wheel logic
        colorWheelButton.addActionListener(e -> {
            ColorWheelPopUpWindow popUpWindow =
                    new ColorWheelPopUpWindow(
                            SwingUtilities.getWindowAncestor(panel)
                    );
            popUpWindow.setVisible(true);

            if (popUpWindow.isConfirmed()) {
                Color picked = popUpWindow.getSelectedColor();
                if (upperColorChooserButton.isSelected()) {
                    primaryInteractor.changeColor(new ChangeColorInputData(picked));
                } else {
                    secondaryInteractor.changeColor(new ChangeColorInputData(picked));
                }
            }
            colorWheelButton.setSelected(false); // after toggling wheel button, toggle button off
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
                if (upperColorChooserButton.isSelected()){
                    primaryInteractor.changeColor(new ChangeColorInputData(solidColor));
                } else {
                    secondaryInteractor.changeColor((new ChangeColorInputData(solidColor)));
                }
                swatch.setSelected(false); // clear toggle state so it doesn't stay
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
