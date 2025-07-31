package view;

import entity.DrawingCanvas;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackState;
import interface_adapter.goback.GoBackViewModel;
import view.MidMenuBar.MidMenuBarBuilder;
import view.TopMenuBar.MenuActionListener;
import view.TopMenuBar.TopMenuBarBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CanvasView extends JPanel implements ActionListener, MenuActionListener {
    private final String viewName = "canvas";
    private final GoBackViewModel goBackViewModel;
    private final GoBackController goBackController;
    private final DrawingCanvas canvas;

    public CanvasView(GoBackViewModel goBackViewModel, GoBackController goBackController) {
        this.goBackViewModel = goBackViewModel;
        this.goBackController = goBackController;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));

        this.canvas = new DrawingCanvas();
        TopMenuBarBuilder topMenuBarBuilder = new TopMenuBarBuilder(canvas);
        JMenuBar menuBar = topMenuBarBuilder.getMenuBar();
        topMenuBarBuilder.setMenuActionListener(this);
        this.add(menuBar, BorderLayout.NORTH);

        MidMenuBarBuilder midMenuBarBuilder = new MidMenuBarBuilder(canvas);
        JPanel panel = midMenuBarBuilder.getPanel();
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(panel);
        bottomPanel.add(canvas);
        this.add(bottomPanel, BorderLayout.CENTER);
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

    public String getViewName() {
        return viewName;
    }

    @Override
    public void onMenuItemSelected(String command) {
        if ("goBack".equals(command) || "logOut".equals(command)) {
            final GoBackState currentState = goBackViewModel.getState();

            goBackController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    command
            );
        }
    }
}
