package entity.menuBar.fileMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class fileButton{

    JMenu menu;

    public fileButton(){
        saveButton saveButton = new saveButton();
        openButton openButton = new openButton();
        saveAsButton saveAsButton = new saveAsButton();
        joinLinkButton joinLinkButton = new joinLinkButton();

        JMenuItem saveMenu = saveButton.getMenuItem();
        JMenuItem openMenu = openButton.getMenuItem();
        JMenuItem saveAsMenu = saveAsButton.getMenuItem();
        JMenuItem joinLinkMenu = joinLinkButton.getMenuItem();

        menu = new JMenu("File");
        menu.add(saveMenu);
        menu.add(openMenu);
        menu.add(saveAsMenu);
        menu.add(joinLinkMenu);

        menu.setMnemonic(KeyEvent.VK_F);
    }

    public JMenu getMenu(){
        return menu;
    }



}
