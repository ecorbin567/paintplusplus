package entity;

import entity.menuBar.fileMenu.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuBarBuilder{
    fileButton fileButton;
    editButton editButton;
    viewButton viewButton;

    JMenuBar menuBar;
    public menuBarBuilder() {
        menuBar = new JMenuBar();
        fileButton = new fileButton();
        JMenu fileMenu = fileButton.getFileMenu();
        menuBar.add(fileMenu);

        editButton = new editButton();
        JMenu editMenu = editButton.getEditMenu();
        menuBar.add(editMenu);

        viewButton = new viewButton();
        JMenu viewMenu = viewButton.getViewMenu();
        menuBar.add(viewMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}


