package view.TopMenuBar;

import entity.DrawingCanvas;
import view.TopMenuBar.EditMenu.EditButton;
import view.TopMenuBar.FileMenu.FileButton;
import view.TopMenuBar.ViewMenu.ViewButton;

import javax.swing.*;
import java.awt.*;

public class TopMenuBarBuilder {
    DrawingCanvas drawingCanvas;
    FileButton FileButton;
    EditButton EditButton;
    ViewButton ViewButton;

    JMenuBar menuBar;
    public TopMenuBarBuilder(DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas;
        menuBar = new JMenuBar();
        FileButton = new FileButton(drawingCanvas);
        JMenu fileMenu = FileButton.getMenu();
        menuBar.add(fileMenu);

        EditButton = new EditButton(this.drawingCanvas);
        JMenu editMenu = EditButton.getEditMenu();
        menuBar.add(editMenu);

        ViewButton = new ViewButton();
        JMenu viewMenu = ViewButton.getViewMenu();
        menuBar.add(viewMenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}


