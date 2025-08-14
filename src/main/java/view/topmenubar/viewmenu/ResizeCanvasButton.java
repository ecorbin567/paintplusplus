package view.topmenubar.viewmenu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;

public class ResizeCanvasButton extends JMenu {
    public ResizeCanvasButton(DrawingView drawingView, TopMenuFacade controller) {
        super("Resize Canvas");
        setMnemonic(KeyEvent.VK_R);

        add(new ZoomInButton(drawingView, controller).getMenuItem());
        add(new ZoomOutButton(drawingView, controller).getMenuItem());
    }
}
