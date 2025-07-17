//package entity.topMenuBar;
//
//import entity.topMenuBar.editMenu.*;
//import entity.topMenuBar.fileMenu.*;
//import entity.topMenuBar.viewMenu.*;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class topMenuBarBuilder {
//    fileButton fileButton;
//    editButton editButton;
//    viewButton viewButton;
//
//    JMenuBar menuBar;
//    public topMenuBarBuilder() {
//        menuBar = new JMenuBar();
//        menuBar.setBackground(Color.RED);
//        fileButton = new fileButton();
//        JMenu fileMenu = fileButton.getMenu();
//        menuBar.add(fileMenu);
//
//        editButton = new editButton();
//        JMenu editMenu = editButton.getEditMenu();
//        menuBar.add(editMenu);
//
//        viewButton = new viewButton();
//        JMenu viewMenu = viewButton.getViewMenu();
//        menuBar.add(viewMenu);
//    }
//
//    public JMenuBar getMenuBar() {
//        return menuBar;
//    }
//
//    public static void main (String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        topMenuBarBuilder topMenuBarBuilder = new topMenuBarBuilder();
//        frame.add(topMenuBarBuilder.getMenuBar());
//        frame.pack();
//        frame.setVisible(true);
//    }
//}
//
//
