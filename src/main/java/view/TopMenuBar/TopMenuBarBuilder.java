package view.TopMenuBar;

import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.DrawingView;
import view.TopMenuBar.EditMenu.EditButton;
import view.TopMenuBar.FileMenu.FileButton;
import view.TopMenuBar.ViewMenu.ViewButton;

import javax.swing.*;

public class TopMenuBarBuilder {
    FileButton FileButton;
    EditButton EditButton;
    ViewButton ViewButton;

    JMenuBar menuBar;
    public TopMenuBarBuilder(DrawingView drawingView, CanvasController canvasController) {
        menuBar = new JMenuBar();

        FileButton = new FileButton(drawingView, canvasController);
        JMenu fileMenu = FileButton.getMenu();
        menuBar.add(fileMenu);

        EditButton = new EditButton(drawingView, canvasController);
        JMenu editMenu = EditButton.getEditMenu();
        menuBar.add(editMenu);

        ViewButton = new ViewButton(drawingView, canvasController);
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


