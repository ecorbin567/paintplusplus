package app;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Paint++");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        DrawingCanvas canvas = new DrawingCanvas();

        final JPanel buttons = new JPanel();
        JButton erase = new JButton("Erase");
        erase.addActionListener(evt -> canvas.erase());
        buttons.add(erase);
        JButton paint = new JButton("Paint");
        paint.addActionListener(evt -> canvas.paint());
        buttons.add(paint);
        JButton undo = new JButton("Undo");
        undo.addActionListener(evt -> canvas.undo());
        buttons.add(undo);
        JButton redo = new JButton("Redo");
        redo.addActionListener(evt -> canvas.redo());
        buttons.add(redo);

        frame.add(buttons, BorderLayout.NORTH);
        frame.add(canvas);

        frame.setVisible(true);
    }
}
