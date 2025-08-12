package view.topmenubar.viewmenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ResizeCanvasButton extends JMenu {
    public ResizeCanvasButton(DrawingView drawingView, TopMenuFacade controller) {
        super("Resize Canvas");
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(drawingView, controller).getMenuItem());
        add(new ZoomOutButton(drawingView, controller).getMenuItem());
    }
}
