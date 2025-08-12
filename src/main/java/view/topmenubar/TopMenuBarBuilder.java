package view.topmenubar;

import interface_adapter.topmenu.TopMenuFacade;
import view.DrawingView;
import view.topmenubar.editmenu.EditButton;
import view.topmenubar.filemenu.FileButton;
import view.topmenubar.viewmenu.ViewButton;

import javax.swing.*;

/**
 * Builds the top menu bar.
 */
public class TopMenuBarBuilder {
    FileButton fileButton;
    EditButton editButton;
    ViewButton viewButton;

    JMenuBar menuBar;

    /**
     * Creates a TopMenuBarBuilder.
     *
     * @param drawingView the drawing view
     * @param controllers the top menu controllers
     */
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

    /**
     * @return the menu bar
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Sets the menu action listener for the file menu.
     *
     * @param listener the menu action listener
     */
    public void setMenuActionListener(MenuActionListener listener) {
        fileButton.setMenuActionListener(listener);
    }
}
