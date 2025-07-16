package entity.menuBar.viewMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class viewButton {
    JMenu menu;
    public viewButton(){
        resizeButton resizeButton = new resizeButton();
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
