package view.topmenubar.filemenu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;
import view.topmenubar.MenuActionListener;

public class FileButton {

    private final JMenu menu;
    private final GoBackButton goBackButton;
    private final LogOutButton logOutButton;

    public FileButton(DrawingView drawingView, TopMenuFacade controller) {
        SaveAsButton saveAsButton = new SaveAsButton(drawingView, controller);
        goBackButton = new GoBackButton();
        logOutButton = new LogOutButton();
        SaveOnlineButton saveOnlineButton = new SaveOnlineButton(drawingView, controller);

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

    public JMenu getMenu() {
        return menu;
    }

    public void setMenuActionListener(MenuActionListener listener) {
        logOutButton.setMenuActionListener(listener);
        goBackButton.setMenuActionListener(listener);
    }

}
