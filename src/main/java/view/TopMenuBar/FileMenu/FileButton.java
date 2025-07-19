package view.TopMenuBar.FileMenu;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileButton {

    JMenu menu;

    public FileButton(DrawingCanvas canvas){
        SaveButton saveButton = new SaveButton(canvas);
        OpenButton openButton = new OpenButton();
        SaveAsButton saveAsButton = new SaveAsButton();
        JoinLinkButton joinLinkButton = new JoinLinkButton();

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
