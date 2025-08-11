package view.TopMenuBar.ViewMenu;

import interface_adapter.topmenu.TopMenuFacade;
import interface_adapter.topmenu.TopMenuFacadeImpl;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ZoomOutButton {
    private final JMenuItem menuItem;

    public ZoomOutButton(DrawingView drawingView, TopMenuFacade controller){
        menuItem = new JMenuItem("Zoom Out");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        menuItem.setActionCommand("Zoom Out");

        menuItem.addActionListener(e -> {
            controller.zoomIn();
            drawingView.repaint();
        });
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
