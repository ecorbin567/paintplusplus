package view.MidMenuBar.EraserButtonGroup;

import entity.DrawingCanvas;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class ErasePopUp {

    JPopupMenu popupMenu;
    JSlider slider;
    DrawingCanvas canvas;

    public ErasePopUp(DrawingCanvas canvas) {
        this.canvas = canvas;
        popupMenu = new JPopupMenu();
        popupMenu.setOpaque(true);

        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        slider.setSize(100, 100);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener cl = e ->{
            JSlider x = (JSlider) e.getSource();
            canvas.setEraserSize(x.getValue());
        };
        slider.addChangeListener(cl);
        popupMenu.add(slider);

    }

    public JPopupMenu getPopupMenu(){
        return this.popupMenu;
    }
}
