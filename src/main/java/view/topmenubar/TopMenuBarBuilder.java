package view.topmenubar;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;
import view.topmenubar.editmenu.EditButton;
import view.topmenubar.filemenu.FileButton;
import view.topmenubar.viewmenu.ViewButton;

import javax.swing.*;

public class TopMenuBarBuilder {
    FileButton FileButton;
    EditButton EditButton;
    ViewButton ViewButton;

    JMenuBar menuBar;
    public TopMenuBarBuilder(DrawingView drawingView, TopMenuFacade controllers) {
        menuBar = new JMenuBar();

        FileButton = new FileButton(drawingView, controllers);
        JMenu fileMenu = FileButton.getMenu();
        menuBar.add(fileMenu);

        EditButton = new EditButton(drawingView, controllers);
        JMenu editMenu = EditButton.getEditMenu();
        menuBar.add(editMenu);

        ViewButton = new ViewButton(drawingView, controllers);
        JMenu viewMenu = ViewButton.getViewMenu();
        menuBar.add(viewMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuActionListener(MenuActionListener listener) {
        FileButton.setMenuActionListener(listener);
    }
}


