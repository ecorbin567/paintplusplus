package view.topmenubar.viewmenu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

public class ZoomOutButton {
    private final JMenuItem menuItem;

    public ZoomOutButton(DrawingView drawingView, TopMenuFacade controller) {
        menuItem = new JMenuItem("Zoom Out");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom Out");

        menuItem.addActionListener(e -> {
            controller.zoomOut();
            drawingView.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
