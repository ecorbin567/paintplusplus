package view.TopMenuBar.FileMenu;

import interface_adapter.canvas.CanvasController;
import view.DrawingView;
import view.TopMenuBar.MenuActionListener;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileButton {

    private final JMenu menu;
    private final GoBackButton goBackButton;
    private final LogOutButton logOutButton;


    public FileButton(DrawingView drawingView, CanvasController canvasController) {
        SaveAsButton saveAsButton = new SaveAsButton(drawingView, canvasController);
        goBackButton = new GoBackButton();
        logOutButton = new LogOutButton();
        SaveOnlineButton saveOnlineButton = new SaveOnlineButton(drawingView, canvasController);

        JMenuItem saveAsMenu = saveAsButton.getMenuItem();
        JMenuItem goBackMenu = goBackButton.getMenuItem();
        JMenuItem logOutMenu = logOutButton.getMenuItem();
        JMenuItem saveOnlineMenu = saveOnlineButton.getMenuItem();

        menu = new JMenu("File");

        menu.add(saveAsMenu);
        menu.add(saveOnlineMenu);
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
