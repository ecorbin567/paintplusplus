package entity.TopMenuBar.viewMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewButton {
    JMenu menu;
    public ViewButton(){
        ResizeButton resizeButton = new ResizeButton();
        JMenuItem resizeItem = resizeButton.getMenuItem();

        menu = new JMenu("View");
        menu.setMnemonic(KeyEvent.VK_V);
        menu.setActionCommand("view");
        menu.add(resizeItem);
    }

    public JMenu getViewMenu() {
        return menu;
    }
}
