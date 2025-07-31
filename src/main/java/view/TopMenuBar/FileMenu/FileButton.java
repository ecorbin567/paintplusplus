package view.TopMenuBar.FileMenu;

import entity.DrawingCanvas;
import view.TopMenuBar.MenuActionListener;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileButton {

    JMenu menu;
    private final GoBackButton goBackButton;
    private final LogOutButton logOutButton;

    public FileButton(DrawingCanvas canvas){
        SaveButton saveButton = new SaveButton(canvas);
        SaveAsButton saveAsButton = new SaveAsButton();
        goBackButton = new GoBackButton();
        logOutButton = new LogOutButton();

        JMenuItem saveMenu = saveButton.getMenuItem();
        JMenuItem saveAsMenu = saveAsButton.getMenuItem();
        JMenuItem goBackMenu = goBackButton.getMenuItem();
        JMenuItem logOutMenu = logOutButton.getMenuItem();

        menu = new JMenu("File");

        menu.add(saveMenu);
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
