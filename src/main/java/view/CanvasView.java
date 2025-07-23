package view;

import entity.DrawingCanvas;
import interface_adapter.canvas.CanvasViewModel;
import view.MidMenuBar.MidMenuBarBuilder;
import view.TopMenuBar.TopMenuBarBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CanvasView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "canvas";
    private final CanvasViewModel canvasViewModel;
    private final DrawingCanvas canvas;

    public CanvasView(CanvasViewModel canvasViewModel) {
        this.canvasViewModel = canvasViewModel;
        this.canvasViewModel.addPropertyChangeListener(this);

        this.canvas = new DrawingCanvas();
        TopMenuBarBuilder topMenuBarBuilder = new TopMenuBarBuilder(canvas);
        JMenuBar menuBar = topMenuBarBuilder.getMenuBar();
        this.add(menuBar);

        MidMenuBarBuilder midMenuBarBuilder = new MidMenuBarBuilder(canvas);
        JPanel panel = midMenuBarBuilder.getPanel();
        this.add(panel, BorderLayout.NORTH);

        this.add(canvas, BorderLayout.CENTER);
    }

    public DrawingCanvas getCanvas() {
        return canvas;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getPropertyName().equals("state")) {
//            final LoggedInState state = (LoggedInState) evt.getNewValue();
//            username.setText(state.getUsername());
//        }
//        else if (evt.getPropertyName().equals("password")) {
//            final LoggedInState state = (LoggedInState) evt.getNewValue();
//            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
//        }

    }

    public String getViewName() {
        return viewName;
    }
}
