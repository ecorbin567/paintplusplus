package view.topmenubar.viewmenu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

public class ViewButton {
    private final JMenu menu;

    public ViewButton(DrawingView drawingView, TopMenuFacade controller) {
        final ResizeCanvasButton resizeButton = new ResizeCanvasButton(drawingView, controller);
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.setActionCommand("view");
        menu.add(resizeButton);
    }

    public JMenu getViewMenu() {
        return menu;
    }
}
