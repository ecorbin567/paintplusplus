package app;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Draw with Mouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        DrawingCanvas canvas = new DrawingCanvas();
        frame.add(canvas);

        frame.setVisible(true);
    }
}
