package view.TopMenuBar;

import entity.DrawingCanvas;
import interface_adapter.canvas.CanvasController;
import view.CanvasView;
import view.TopMenuBar.EditMenu.EditButton;
import view.TopMenuBar.FileMenu.FileButton;
import view.TopMenuBar.ViewMenu.ViewButton;

import javax.swing.*;

public class TopMenuBarBuilder {
    CanvasView canvasView;
    CanvasController canvasController;
    FileButton FileButton;
    EditButton EditButton;
    ViewButton ViewButton;

    JMenuBar menuBar;
    public TopMenuBarBuilder(CanvasView canvasView, CanvasController canvasController) {
        this.canvasView = canvasView;
        this.canvasController = canvasController;
        menuBar = new JMenuBar();
        FileButton = new FileButton(this.canvasView, this.canvasController);
        JMenu fileMenu = FileButton.getMenu();
        menuBar.add(fileMenu);

        EditButton = new EditButton(this.canvasController);
        JMenu editMenu = EditButton.getEditMenu();
        menuBar.add(editMenu);

        ViewButton = new ViewButton(this.canvasController);
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


