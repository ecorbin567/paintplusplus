package entity.menuBar.viewMenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class viewButton {
    JMenu fileMenu;
    public viewButton(){
        fileMenu = new JMenu("View");
        fileMenu.setMnemonic(KeyEvent.VK_V);
        fileMenu.setActionCommand("view");


    }
}
