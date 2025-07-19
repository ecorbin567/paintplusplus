package app;

import entity.DrawingCanvas;
import view.MidMenuBar.MidMenuBarBuilder;
import view.TopMenuBar.TopMenuBarBuilder;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Paint++");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        DrawingCanvas canvas = new DrawingCanvas();
        TopMenuBarBuilder topMenuBarBuilder = new TopMenuBarBuilder(canvas);
        JMenuBar menuBar = topMenuBarBuilder.getMenuBar();
        frame.setJMenuBar(menuBar);
        MidMenuBarBuilder midMenuBarBuilder = new MidMenuBarBuilder(canvas);
        JPanel panel = midMenuBarBuilder.getPanel();
//        JPanel panel = new JPanel(new BorderLayout());
//        JButton eraseButton = new JButton("Erase");
//        eraseButton.addActionListener(evt -> canvas.erase());
//        panel.add(eraseButton, BorderLayout.EAST);
//        JButton pencilButton = new JButton("Pencil");
//        pencilButton.addActionListener(evt -> canvas.paint());
//        panel.add(pencilButton, BorderLayout.WEST);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(canvas);
        frame.setVisible(true);
    }
}
