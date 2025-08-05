package view.TopMenuBar.FileMenu;

import entity.DrawingCanvas;
import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.TopMenuBar.MenuActionListener;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileButton {

    private final JMenu menu;
    private final GoBackButton goBackButton;
    private final LogOutButton logOutButton;
    private CanvasView canvasView;
    private CanvasController canvasController;

    public FileButton(CanvasView canvasView, CanvasController canvasController) {
        this.canvasView = canvasView;
        this.canvasController = canvasController;
        SaveAsButton saveAsButton = new SaveAsButton(this.canvasView, this.canvasController);
        goBackButton = new GoBackButton();
        logOutButton = new LogOutButton();

        JMenuItem saveAsMenu = saveAsButton.getMenuItem();
        JMenuItem goBackMenu = goBackButton.getMenuItem();
        JMenuItem logOutMenu = logOutButton.getMenuItem();

        menu = new JMenu("File");

        menu.add(saveAsMenu);
        menu.add(goBackMenu);
        menu.add(logOutMenu);

        menu.setMnemonic(KeyEvent.VK_F);
    }

    public JMenu getMenu(){
        return menu;
    }

    public void setMenuActionListener(MenuActionListener listener) {
        logOutButton.setMenuActionListener(listener);
        goBackButton.setMenuActionListener(listener);
    }

}
