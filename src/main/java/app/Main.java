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

        frame.add(buttons, BorderLayout.NORTH);
        frame.add(canvas);

        canvas.setPreferredSize(new Dimension(200, 100));

        frame.setVisible(true);
    }
}
