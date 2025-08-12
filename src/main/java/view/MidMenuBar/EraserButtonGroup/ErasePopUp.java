package view.midmenubar.eraserbuttongroup;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInputData;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class ErasePopUp {
    private final ToolEnum toolName = ToolEnum.ERASER;
    private final JPopupMenu popupMenu;
    private final JSlider slider;

    public ErasePopUp(CanvasController canvasController) {
        popupMenu = new JPopupMenu();
        popupMenu.setOpaque(true);

        slider = new JSlider(SwingConstants.VERTICAL, 0, 100, 0);
        slider.setSize(100, 100);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener cl = e ->{
            JSlider sliderValue = (JSlider) e.getSource();
            float value = sliderValue.getValue();
            canvasController.handleEraserSliderButton(toolName, value);
        };

        slider.addChangeListener(cl);
        popupMenu.add(slider);

    }

    public JPopupMenu getPopupMenu(){
        return this.popupMenu;
    }
}
