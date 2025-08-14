package view.midmenubar.eraserbuttongroup;

import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import entity.ToolEnum;
import interface_adapter.canvas.CanvasController;

public class ErasePopUp {
    private static final ToolEnum toolName = ToolEnum.ERASER;
    private final JPopupMenu popupMenu;

    public ErasePopUp(CanvasController canvasController) {
        popupMenu = new JPopupMenu();
        popupMenu.setOpaque(true);

        JSlider slider = new JSlider(SwingConstants.VERTICAL, 0, 100, 0);
        slider.setSize(100, 100);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        ChangeListener cl = e -> {
            JSlider sliderValue = (JSlider) e.getSource();
            float value = sliderValue.getValue();
            canvasController.handleSlider(toolName, value);
        };

        slider.addChangeListener(cl);
        popupMenu.add(slider);

    }

    public JPopupMenu getPopupMenu() {
        return this.popupMenu;
    }
}
