package view;

import entity.DrawingCanvas;
import view.MidMenuBar.EraseButton;
import view.MidMenuBar.MidMenuBarBuilder;
import view.TopMenuBar.TopMenuBarBuilder;

import javax.swing.*;
import java.awt.*;

public class ViewManager {
    public ViewManager(){
        JFrame frame = new JFrame("Paint++");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        DrawingCanvas canvas = new DrawingCanvas();
        TopMenuBarBuilder topMenuBarBuilder = new TopMenuBarBuilder(canvas);
        JMenuBar menuBar = topMenuBarBuilder.getMenuBar();
        frame.setJMenuBar(menuBar);

        MidMenuBarBuilder midMenuBarBuilder = new MidMenuBarBuilder(canvas);
        JPanel panel = midMenuBarBuilder.getPanel();
        frame.add(panel, BorderLayout.NORTH);

        frame.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
    }
//    Testing Area:
//    public static void main(String[] args) {
//        new ViewManager();
//    }
}
