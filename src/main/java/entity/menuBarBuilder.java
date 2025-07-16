package entity;

import entity.menuBar.editMenu.*;
import entity.menuBar.fileMenu.*;
import entity.menuBar.viewMenu.*;

import javax.swing.*;

public class menuBarBuilder{
    fileButton fileButton;
    editButton editButton;
    viewButton viewButton;

    JMenuBar menuBar;
    public menuBarBuilder() {
        menuBar = new JMenuBar();
        fileButton = new fileButton();
        JMenu fileMenu = fileButton.getMenu();
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

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBarBuilder menuBarBuilder = new menuBarBuilder();
        frame.add(menuBarBuilder.getMenuBar());
        frame.pack();
        frame.setVisible(true);
    }
}


