package view.TopMenuBar.ViewMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewButton {
    private final JMenu menu;
    public ViewButton(DrawingCanvas drawingCanvas){

        ResizeButton resizeButton = new ResizeButton(drawingCanvas);

        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.setActionCommand("view");

        menu.add(resizeButton);



    }

    public JMenu getViewMenu() {
        return menu;
    }
}
