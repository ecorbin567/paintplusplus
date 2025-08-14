package view.midmenubar.colorbuttonsbar;

import java.awt.Color;

import javax.swing.JToggleButton;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;

public class LowerColorChooserButton extends CircularButton {

    private Color lowerCurrentColor = Color.WHITE;
    private static final ToolEnum toolName = ToolEnum.PENCIL;
    private static final String BUTTON = "LowerColorChooserButton";

    public LowerColorChooserButton(ColorController colorController) {
        super(30);

        setBackground(lowerCurrentColor);
        this.addActionListener(e ->
                colorController.handleColorChangeButton(toolName, lowerCurrentColor, BUTTON));

    }

    public void setCurrentColor(Color c) {
        this.lowerCurrentColor = c;
        setBackground(c);
    }

    public JToggleButton getButton() {
        return this;
    }
}
