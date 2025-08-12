package view.TopMenuBar.ViewMenu;

import interface_adapter.topmenu.TopMenuFacade;
import interface_adapter.topmenu.TopMenuFacadeImpl;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewButton {
    private final JMenu menu;
    public ViewButton(DrawingView drawingView, TopMenuFacade controller) {
        ResizeCanvasButton resizeButton = new ResizeCanvasButton(drawingView, controller);
        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.setActionCommand("view");
        menu.add(resizeButton);
    }

    public JMenu getViewMenu() {
        return menu;
    }
}
