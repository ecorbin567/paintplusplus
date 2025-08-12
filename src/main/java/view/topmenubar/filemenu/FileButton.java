package view.topmenubar.filemenu;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;
import view.topmenubar.MenuActionListener;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * File menu button.
 */
public class FileButton {

    private final JMenu menu;
    private final GoBackButton goBackButton;
    private final LogOutButton logOutButton;

    /**
     * Creates the file menu with save, save online, go back, and log out options.
     *
     * @param drawingView the drawing view
     * @param controller the top menu controller
     */
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

    /**
     * @return the file menu
     */
    public JMenu getMenu(){
        return menu;
    }

    /**
     * Sets the menu action listener for go back and log out options.
     *
     * @param listener the menu action listener
     */
    public void setMenuActionListener(MenuActionListener listener) {
        logOutButton.setMenuActionListener(listener);
        goBackButton.setMenuActionListener(listener);
    }
}
