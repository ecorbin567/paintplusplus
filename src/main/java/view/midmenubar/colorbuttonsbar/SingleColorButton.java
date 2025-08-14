package view.midmenubar.colorbuttonsbar;

import java.awt.Color;

import javax.swing.AbstractButton;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;

public class SingleColorButton extends CircularButton {
    private static final ToolEnum toolName = ToolEnum.PENCIL;

    public SingleColorButton(Color c, ColorController colorController,
                             UpperColorChooserButton upperColorChooserButton,
                             LowerColorChooserButton lowerColorChooserButton) {
        super(20);
        setBackground(c);
        this.addActionListener(e -> {
            colorController.handleColorChangeButton(toolName, c);
            if (upperColorChooserButton.isSelected()) {
                upperColorChooserButton.setCurrentColor(c);
            }
            else {
                lowerColorChooserButton.setCurrentColor(c);
            }
            this.setSelected(false);
        });
    }

    public AbstractButton getButton() {
        return this;
    }
}
