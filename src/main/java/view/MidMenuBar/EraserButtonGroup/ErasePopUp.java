package view.MidMenuBar.EraserButtonGroup;

import use_case.toolselection.ToolSelectionInputBoundary;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class ErasePopUp {

    JPopupMenu popupMenu;
    JSlider slider;
    ToolSelectionInputBoundary inputBoundary;


    public ErasePopUp(ToolSelectionInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
        popupMenu = new JPopupMenu();
        popupMenu.setOpaque(true);

        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        slider.setSize(100, 100);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener cl = e ->{
            JSlider sliderValue = (JSlider) e.getSource();
            float value = sliderValue.getValue();
            inputBoundary.selectSize(value);
        };

        slider.addChangeListener(cl);
        popupMenu.add(slider);

    }

    public JPopupMenu getPopupMenu(){
        return this.popupMenu;
    }
}
