package entity.TopMenuBar.fileMenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenButton implements ActionListener{
    JMenuItem menuItem;

    public OpenButton() {
        menuItem = new JMenuItem("Open");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.ALT_DOWN_MASK));
        menuItem.setActionCommand("open");
        menuItem.addActionListener(this);
    }
    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public void handleAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images"
                , "png");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileChooser.getSelectedFile();
        }
    }

    public void actionPerformed(ActionEvent e) {
        handleAction();
    }

}
