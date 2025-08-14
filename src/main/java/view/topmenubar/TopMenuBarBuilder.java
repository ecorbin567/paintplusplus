package view.topmenubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;
import view.topmenubar.editmenu.EditButton;
import view.topmenubar.filemenu.FileButton;
import view.topmenubar.viewmenu.ViewButton;

public class TopMenuBarBuilder {
    FileButton fileButton;
    EditButton editButton;
    ViewButton viewButton;

    JMenuBar menuBar;

    public TopMenuBarBuilder(DrawingView drawingView, TopMenuFacade controllers) {
        menuBar = new JMenuBar();

        fileButton = new FileButton(drawingView, controllers);
        JMenu fileMenu = fileButton.getMenu();
        menuBar.add(fileMenu);

        editButton = new EditButton(drawingView, controllers);
        JMenu editMenu = editButton.getEditMenu();
        menuBar.add(editMenu);

        viewButton = new ViewButton(drawingView, controllers);
        JMenu viewMenu = viewButton.getViewMenu();
        menuBar.add(viewMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuActionListener(MenuActionListener listener) {
        fileButton.setMenuActionListener(listener);
    }
}


