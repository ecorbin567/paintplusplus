package entity.menuBar.fileMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class fileButton{
    private final saveButton saveButton;
    private final openButton openButton;
    private final saveAsButton saveAsButton;
    private final joinLinkButton joinLinkButton;
    JMenu fileMenu;

    public fileButton(){
        saveButton = new saveButton();
        openButton = new openButton();
        saveAsButton = new saveAsButton();
        joinLinkButton = new joinLinkButton();

        JMenuItem saveMenu = saveButton.getMenuItem();
        JMenuItem openMenu = openButton.getMenuItem();
        JMenuItem saveAsMenu = saveAsButton.getMenuItem();
        JMenuItem joinLinkMenu = joinLinkButton.getMenuItem();

        fileMenu = new JMenu("File");
        fileMenu.add(saveMenu);
        fileMenu.add(openMenu);
        fileMenu.add(saveAsMenu);
        fileMenu.add(joinLinkMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);
    }

    public saveButton getSaveButton(){
        return saveButton;
    }

    public openButton getOpenButton(){
        return openButton;
    }

    public saveAsButton getSaveAsButton(){
        return saveAsButton;
    }

    public joinLinkButton getJoinLinkButton(){
        return joinLinkButton;
    }

    public JMenu getFileMenu(){
        return fileMenu;
    }



}
